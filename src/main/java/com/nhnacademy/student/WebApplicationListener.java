package com.nhnacademy.student;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.Random;

@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();

        for (int i=1; i<=10; i++){
            Random random = new Random();
            int age = 20 + random.nextInt(11);

            Student student = new Student(
                    String.valueOf(i),
                    "Student" + i,
                    (i % 2 == 0) ? "Male" : "Female",
                    age
            );

            studentRepository.save(student);
        }
        context.setAttribute("studentRepository", studentRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){

    }
}
