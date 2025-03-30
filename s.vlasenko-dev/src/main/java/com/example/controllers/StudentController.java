package com.example.controllers;

import com.example.models.RawStudent;
import com.example.services.StudentService;
import com.example.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.models.ErrorResponse;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService = new StudentService();

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student); // 200 OK
        } else {
            // Возвращаем ошибку 404 с сообщением
            ErrorResponse errorResponse = new ErrorResponse("Student not found with id: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody RawStudent rawStudent) {
        try {
            studentService.addStudent(rawStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(rawStudent); // 201 Created
        } catch (Exception e) {
            // Возвращаем ошибку 500 с сообщением
            ErrorResponse errorResponse = new ErrorResponse("Failed to add student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            // Возвращаем ошибку 404 с сообщением
            ErrorResponse errorResponse = new ErrorResponse("Student not found with id: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}