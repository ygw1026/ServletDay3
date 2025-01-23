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

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        // ControllerFactory 를 사용해 Command 객체 가져옴
        try{
            ControllerFactory controllerFactory = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
            Command command = (Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
            //실제 요청을 처리한 servlet 이 'view' 라는 request 속성값으로 view 를 전달해 줌.
            String view = command.execute(req, resp);
        if (command == null) {
            throw new ServletException("해당 경로에 대한 Command 를 찾을 수 없습니다: " + req.getServletPath());
        }
            if (view != null) {
                if (view.startsWith(REDIRECT_PREFIX)) {
                    // todo  `redirect:`로 시작하면 redirect 처리.
                    String redirectUrl = view.substring(REDIRECT_PREFIX.length() + 1);
                    log.info("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                    resp.sendRedirect(redirectUrl);
                } else {
                    //todo redirect 아니면 JSP 에게 view 처리를 위임하여 그 결과를 include 시킴.
                    log.info("including to JSP page: {}", view);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(view);
                    dispatcher.include(req, resp);
                }
            }
        } catch (Exception ex) {
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리
            log.error("요청 처리 중 오류 발생", ex);
//            req.setAttribute("errorMessage", ex.getMessage());
            //todo  forward - /error.jsp
//            req.getRequestDispatcher("/error.do").forward(req, resp);
        }
    }

//    private Command resolveServlet(String servletPath, String method) {
//        Command command = null;
//
//        // equalsIgnoreCase : 대소문자를 구분하지 않고 문자열을 비교.
//        if ("/com/nhnacademy/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
//            command = new StudentDeleteController();
//        } else if("/com/nhnacademy/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
//            command = new StudentRegisterGetController();
//        }else if("/com/nhnacademy/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
//            command = new StudentRegisterPostController();
//        }else if("/com/nhnacademy/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
//            command = new StudentListController();
//        } else if("/com/nhnacademy/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
//            command = new StudentUpdateGetController();
//        } else if("/com/nhnacademy/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
//            command = new StudentUpdatePostController();
//        }else if("/com/nhnacademy/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
//            command = new StudentViewController();
//        }
//
//        //todo 실행할 servlet 결정하기
//        return command;
//    }
}
