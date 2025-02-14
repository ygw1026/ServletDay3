package com.nhnacademy.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

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

        return "/student/view.jsp";
    }
}
