package com.nhnacademy.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentRegisterGetController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        return "/student/register.jsp";
    }
}
