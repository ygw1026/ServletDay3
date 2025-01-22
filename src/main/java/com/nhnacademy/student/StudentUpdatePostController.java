package com.nhnacademy.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StudentUpdatePostController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        //todo null check
        if (id == null || name==null || gender==null || ageStr==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can not update. Missing required fields");
            return null;
        }

        // 나이를 정수로 변환
        int age;
        try{
            age = Integer.parseInt(ageStr);
        } catch(NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid age format");
            return null;
        }

        //todo student 저장
        Student student = new Student(id, name, gender, age);

        if (!studentRepository.existById(id)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
            return null;
        }

        studentRepository.update(student);

        //todo /student/view?id=student1 <-- redirect

        return "/student/list.jsp";
    }
}
