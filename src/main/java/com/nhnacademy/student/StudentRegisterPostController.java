package com.nhnacademy.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.RemoteException;

public class StudentRegisterPostController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageStr = req.getParameter("age");

        if (id == null || name == null || gender == null || ageStr == null) {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
            } catch (IOException e) {
                throw new RuntimeException("Error while sending error response", e);
            }
            return null;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid age format");
            } catch (IOException ex) {
                throw new RuntimeException("Error while sending error response", ex);
            }
            return null;
        }

        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        return "redirect:/student/list.do";
    }
}
