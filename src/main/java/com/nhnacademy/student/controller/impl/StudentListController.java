package com.nhnacademy.student.controller.impl;

import com.nhnacademy.student.common.annotation.RequestMapping;
import com.nhnacademy.student.common.annotation.StopWatch;
import com.nhnacademy.student.controller.Command;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@StopWatch
@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();

        // 아이디 기준으로 오름차순 정렬
        studentList.sort((s1, s2) -> Integer.compare(Integer.parseInt(s1.getId()), Integer.parseInt(s2.getId())));

        // 정렬된 학생 목록을 request 에 설정
        req.setAttribute("studentList", studentList);

        return "/student/list.jsp";
    }
}
