package com.nhnacademy.student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    void update(Student student);

    void deleteById(String id);

    Student getStudentById(String id);

    Student getStudentByName(String name);

    List<Student> getStudents();

    boolean existById(String id);
}
