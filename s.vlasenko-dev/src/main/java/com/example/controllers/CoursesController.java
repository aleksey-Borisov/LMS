package com.example.controllers;

import com.example.models.Course;
import com.example.models.RawCourse;
import com.example.models.Topic;
import com.example.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService = new CoursesService();
    
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody RawCourse newCourse) {
        Course created = coursesService.createCourse(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id) {
        try {
            Course course = coursesService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        try {
            coursesService.deleteCourseById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<?> enrollStudent(@PathVariable int courseId, @PathVariable int studentId) {
        try {
            Course updatedCourse = coursesService.enrollStudent(courseId, studentId);
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/{courseId}/unenroll/{studentId}")
    public ResponseEntity<?> unenrollStudent(@PathVariable int courseId, @PathVariable int studentId) {
        try {
            Course updatedCourse = coursesService.unenrollStudent(courseId, studentId);
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/{id}/topics")
    public ResponseEntity<?> addTopic(@PathVariable int id, @RequestBody Topic newTopic) {
        try {
            Course updatedCourse = coursesService.addTopic(id, newTopic);
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
