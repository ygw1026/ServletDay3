package com.nhnacademy.student;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="StudentUpdateServlet", urlPatterns="/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String studentId = req.getParameter("id");

        // id가 null인지 확인
        if (studentId == null || studentId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID is missing");
            return;
        }

        //todo 학생조회
        Student student = studentRepository.getStudentById(studentId);

        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
            return;
        }

        req.setAttribute("student", student);

        //todo forward : /student/register.jsp
        req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        //todo null check
        if (id == null || name==null || gender==null || ageStr==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can not update. Missing required fields");
            return;
        }

        // 나이를 정수로 변환
        int age;
        try{
            age = Integer.parseInt(ageStr);
        } catch(NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid age format");
            return;
        }

        //todo student 저장
        Student student = new Student(id, name, gender, age);

        if (!studentRepository.existById(id)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
            return;
        }

        studentRepository.update(student);

        //todo /student/view?id=student1 <-- redirect
        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}
