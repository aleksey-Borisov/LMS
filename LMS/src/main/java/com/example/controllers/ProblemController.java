package com.example.controllers;

import com.example.models.Problem;
import com.example.services.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService = new ProblemService();

    // Получение задачи по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProblem(@PathVariable int id) {
        try {
            Problem problem = problemService.getProblemById(id);
            return ResponseEntity.ok(problem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Удаление задачи по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProblem(@PathVariable int id) {
        try {
            problemService.deleteProblemById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}