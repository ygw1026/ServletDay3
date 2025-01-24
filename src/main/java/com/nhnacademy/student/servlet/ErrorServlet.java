package com.nhnacademy.student.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name="errorServlet", urlPatterns="/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("status_code", 404);
        req.setAttribute("exception_type", "java.lang.Exception");
        req.setAttribute("message", "Resource not found");
        req.setAttribute("exception", "NullPointerException");
        req.setAttribute("request_uri", "/non-existent-page");
    }
}
