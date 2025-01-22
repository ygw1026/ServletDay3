package com.nhnacademy.student;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="StudentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Student student = null;

        // 학생을 아이디로 조회
        if(id!=null && !id.trim().isEmpty()){
            student = studentRepository.getStudentById(id);
        }

        if(name!=null && !name.trim().isEmpty()){
            student = studentRepository.getStudentByName(name);
        }

        // 조회한 학생이 있다면 학생 정보 반환, 없다면 exception 발생
        if(student != null){
            req.setAttribute("student", student);
        } else{
            req.setAttribute("message", "학생을 찾을 수 없습니다.");
        }

//        req.getRequestDispatcher("/student/view.jsp").forward(req, resp);
        req.setAttribute("view", "/student/view.jsp");
    }
}
