package com.nhnacademy.student.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH="/home/nhnacademy/IdeaProjects/Day3/src/main/json/student.json";

    public JsonStudentRepository(){
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.

        File file = new File(JSON_FILE_PATH);

        if (file.exists()) {
            file.delete();
        }
    }

    private synchronized List<Student> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()){
            return new ArrayList<>();
        }
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            return  objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 처리 오류", e);
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 오류", e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList){
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);

        try(
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        //json String -> Object 형태로 변화 (직렬화)
        List<Student> students = readJsonFile();
        // 학생이 이미 존재하는지 확인
        if (existById(student.getId())) {
            log.error("Student ID {} already exists", student.getId());
        } else {
            // 학생 추가
            students.add(student);
            writeJsonFile(students);
            log.info("Student added: {} {}", student.getId(), student);
        }
    }
    //todo 나머지 method는 직접 구현하기

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        if (existById(student.getId())) {
            students = students.stream()
                    .map(s -> s.getId().equals(student.getId()) ? student : s)
                    .collect(Collectors.toList());
            writeJsonFile(students);
            log.info("Student updated: {} {}", student.getId(), student);
        } else {
            log.error("Student ID {} not found for update", student.getId());
        }
    }


    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        // 학생이 존재하는지 확인 후 삭제
        if (students.removeIf(student -> student.getId().equals(id))) {
            writeJsonFile(students);
            log.info("Student ID {} has been removed", id);
        } else {
            log.error("Student ID {} not found for deletion", id);
        }
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null); // ID로 학생을 찾을 수 없으면 null 반환
    }

    @Override
    public Student getStudentByName(String name) {
        List<Student> students = readJsonFile();
        return students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElse(null); // 이름으로 학생을 찾을 수 없으면 null 반환
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile(); // 모든 학생 반환
    }

    @Override
    public boolean existById(String id) {
        return getStudentById(id) != null;
    }
}
