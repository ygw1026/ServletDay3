package com.nhnacademy.student;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo get parameter  : id , id가 존재하지 않을경우 throw new RuntimeException("...")
        String studentId = req.getParameter("id");
        if(Objects.isNull(studentId)){
            throw new RuntimeException("StudentId Not Exist!!");
        }

        studentRepository.deleteById(studentId);

        req.setAttribute("studentId", studentId);
        req.setAttribute("view", "/student/list.jsp");


//        //todo /student/list <-- redirect
//        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}
