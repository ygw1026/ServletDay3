package com.nhnacademy.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");

        if (id == null || id.isEmpty()){
            throw new RuntimeException("Student ID Not Exist!!");
        }

        studentRepository.deleteById(id);

        return "/student/delete.jsp";
    }
}
