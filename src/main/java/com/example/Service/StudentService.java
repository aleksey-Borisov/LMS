package com.example.Service;

import com.example.model.Student;
import com.example.controllers.StudentController;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;


public class StudentService {
        private static List<Student> students = new ArrayList<>();

        public static void addStudent(Student student) {
            students.add(student);
        }

        public static void deleteStudent(int id) {
        students.remove(findStudentById(id));
    }

        public static Student findStudentById(int id) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == id) {
                    return students.get(i);
                }
            }
            return null;
        }
}



