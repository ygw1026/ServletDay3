package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.common.annotation.RequestMapping;
import com.nhnacademy.student.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(value = "error.do", method = RequestMapping.Method.GET)
public class ErrorController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("status_code", 404);
        req.setAttribute("exception_type", "java.lang.Exception");
        req.setAttribute("message", "Resource not found");
        req.setAttribute("exception", "NullPointerException");
        req.setAttribute("request_uri", "/non-existent-page");

        return "/com/nhnacademy/student/error.jsp";
    }
}
