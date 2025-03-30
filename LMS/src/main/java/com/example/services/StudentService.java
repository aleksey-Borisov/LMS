package com.example.services;

import com.example.models.RawStudent;
import com.example.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public StudentService() {}

    public void addStudent(RawStudent rawStudent) {
        int id = nextId;
        nextId += 1;
        Student student = new Student(id, rawStudent.getLogin(), rawStudent.getFirstName(), rawStudent.getLastName(), rawStudent.getPhoneNumber(), rawStudent.getSolvedProblems());
        students.add(student);
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        Student studentToRemove = findStudentById(id);
        if (studentToRemove != null) {
            return students.remove(studentToRemove);
        }
        return false;
    }

    public void clearStudents() {
        students.clear();
        nextId = 1;
    }
}