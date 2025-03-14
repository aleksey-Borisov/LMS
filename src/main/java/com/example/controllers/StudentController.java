package com.example.controllers;
import com.example.Service.StudentService;
import com.example.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
//    private List<Student> students = new ArrayList<>();
    private int nextId = 100; // считаем что у нас будет не более 100 объектов


    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
//        Student student = StudentService.findStudentById(id);
        return StudentService.findStudentById(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody Student student) {
        student.setId(nextId++);
        StudentService.addStudent(student);
    }

}
