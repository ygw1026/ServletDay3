package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentMap = new ConcurrentHashMap<>();


    @Override
    public void save(Student student) {
        if(existById(student.getId())) {
            log.error("Student ID {} already exist", student.getId());
        } else {
            studentMap.put(student.getId(), student);
            log.info("Student added: {} {}", student.getId(), student);
        }
    }

    @Override
    public void update(Student student) {
        if (existById(student.getId())){
            studentMap.put(student.getId(), student);
            log.info("Student updated: {} {}", student.getId(), student);
        } else {
            log.error("Student ID {} not found for update", student.getId());
        }
    }

    @Override
    public void deleteById(String id) {
        if (studentMap.remove(id) != null){
            log.info("Student ID {} has been removed", id);
        } else{
            log.error("Student ID {} Not Exist!!", id);
        }
    }

    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public boolean existById(String id) {
        return studentMap.containsKey(id);
    }
}
