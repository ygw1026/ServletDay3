package com.nhnacademy.student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/html; charset=UTF-8");

        try {
            //실제 요청 처리할 servlet을 결정
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            //실제 요청을 처리한 servlet 이 'view' 라는 request 속성값으로 view 를 전달해 줌.
            String view = (String) req.getAttribute("view");

            if (view != null) {
                if (view.startsWith(REDIRECT_PREFIX)) {
                    // todo  `redirect:`로 시작하면 redirect 처리.
                    String redirectUrl = view.substring(REDIRECT_PREFIX.length() + 1);
                    log.info("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                    resp.sendRedirect(redirectUrl);
                } else {
                    //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                    log.info("forwarding to JSP page: {}", view);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(view);
                    dispatcher.forward(req, resp);
                }
            }
        } catch (Exception ex) {
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리
            log.error("요청 처리 중 오류 발생", ex);
            req.setAttribute("errorMessage", ex.getMessage());
            req.getRequestDispatcher("/error/error.jsp").forward(req, resp);
            //todo  forward - /error.jsp
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;

        if ("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        }
        if ("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        }
        if ("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        }
        if ("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        }
        if ("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        }

        //todo 실행할 servlet 결정하기
        return processingServlet;
    }
}
