package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.common.annotation.RequestMapping;
import com.nhnacademy.student.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentRegisterGetController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        return "/student/register.jsp";
    }
}
