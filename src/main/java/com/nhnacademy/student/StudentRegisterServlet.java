package com.nhnacademy.student;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@WebServlet(name="StudentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentRepository.getStudents();

        req.setAttribute("studentList", studentList);

        req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // POST 요청 시 폼 데이터 받아오기
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        // 필수 파라미터 체크
        if (id == null || name == null || gender == null || ageStr == null) {
            // 유효하지 않으면 400 응답
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can not register. Missing required fields");
            return;
        }

        // 나이를 정수로 변환
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid age format");
            return;
        }

        // 새로운 Student 객체 생성
        Student student = new Student(id, name, gender, age);

        // 학생 저장
        studentRepository.save(student);

        // 저장 후 학생 리스트 페이지로 리디렉션
        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}
