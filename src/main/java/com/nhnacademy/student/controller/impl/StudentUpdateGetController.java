package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.common.annotation.RequestMapping;
import com.nhnacademy.student.controller.Command;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.GET)
public class StudentUpdateGetController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String studentId = req.getParameter("id");

        // id가 null 인지 확인
        if (studentId == null || studentId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID is missing");
            return null;
        }

        //todo 학생조회
        Student student = studentRepository.getStudentById(studentId);

        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
            return null;
        }

        req.setAttribute("student", student);

        return "/student/register.jsp";
    }
}
