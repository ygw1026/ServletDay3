package com.nhnacademy.student;

import jakarta.servlet.RequestDispatcher;
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
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            //실제 요청 처리할 servlet을 결정
            Command command = resolveServlet(req.getServletPath(), req.getMethod());

            //실제 요청을 처리한 servlet 이 'view' 라는 request 속성값으로 view 를 전달해 줌.
            String view = command.execute(req, resp);

            if (view != null) {
                if (view.startsWith(REDIRECT_PREFIX)) {
                    // todo  `redirect:`로 시작하면 redirect 처리.
                    String redirectUrl = view.substring(REDIRECT_PREFIX.length() + 1);
                    log.info("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                    resp.sendRedirect(redirectUrl);
                } else {
                    //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                    log.info("including to JSP page: {}", view);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(view);
                    dispatcher.include(req, resp);
                }
            }
        } catch (Exception ex) {
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리
            log.error("요청 처리 중 오류 발생", ex);
            req.setAttribute("errorMessage", ex.getMessage());
            //todo  forward - /error.jsp
            req.getRequestDispatcher("/error/error.do").forward(req, resp);
        }
    }

    private Command resolveServlet(String servletPath, String method) {
        Command command = null;

        // equalsIgnoreCase : 대소문자를 구분하지 않고 문자열을 비교.
        if ("/com/nhnacademy/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new StudentDeleteController();
        } else if("/com/nhnacademy/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new StudentRegisterGetController();
        }else if("/com/nhnacademy/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new StudentRegisterPostController();
        }else if("/com/nhnacademy/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new StudentListController();
        } else if("/com/nhnacademy/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new StudentUpdateGetController();
        } else if("/com/nhnacademy/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new StudentUpdatePostController();
        }else if("/com/nhnacademy/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new StudentViewController();
        }

        //todo 실행할 servlet 결정하기
        return command;
    }
}
