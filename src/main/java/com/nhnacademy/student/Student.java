package com.nhnacademy.student;


import java.time.LocalDateTime;
import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String gender;
    private int age;
    private Date createdAt;

    public Student(String id, String name, String gender, int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                '}';
    }
}
