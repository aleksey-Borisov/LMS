package com.example.controllers;

import jakarta.validation.Valid;
import com.example.models.Problem;
import com.example.models.RawProblem;
import com.example.models.Topic;
import com.example.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.models.ErrorResponse;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService = new TopicService();

    @GetMapping("/topics/{id}")
    public ResponseEntity<?> getTopic(@PathVariable int id) {
        Topic topic = topicService.findTopicById(id);
        if (topic != null) {
            return ResponseEntity.ok(topic); // 200 OK
        } else {
            // Возвращаем ошибку 404 с сообщением
            ErrorResponse errorResponse = new ErrorResponse("topic not found with id: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable int id) {
        boolean isDeleted = topicService.deleteTopic(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {

            ErrorResponse errorResponse = new ErrorResponse("Topic not found with id: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping("/topics/{id}/problems")
    public ResponseEntity<?> addProblem(@PathVariable int id, @Valid @RequestBody Problem newProblem) {
        try {
            Topic updatedTopic = topicService.addProblem(id, newProblem);
            return ResponseEntity.ok(updatedTopic);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
