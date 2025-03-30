package com.example.controllers;

import com.example.models.Role;
import com.example.services.AuthService;
import com.example.models.User; // Исправлено: должно быть models, а не model
import com.example.models.RawUser ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/signIn")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> addUser (@Valid @RequestBody RawUser  rawUser ,
                                      @RequestHeader("Authorization") String credentials) {
        try {
            // Проверка роли текущего пользователя
            Role role = authService.getRoleByCredentials(credentials);
            if (role != Role.ADMIN) {
                throw new IllegalArgumentException("Access denied");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        // Создание нового пользователя
        User created = authService.createUser (rawUser );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}