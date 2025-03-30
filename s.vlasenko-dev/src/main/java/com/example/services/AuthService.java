package com.example.services;

import com.example.models.RawUser;
import com.example.models.Role;
import com.example.models.User;

import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AuthService {
    private final List<User> users = new ArrayList<>();
    private int lastId = 0;

    public AuthService() {
        users.add(new User(
                0,
                "admin",
                "admin",
                Role.ADMIN
        ));
    }

    public User createUser(RawUser rawUser) throws IllegalArgumentException {
        int id = lastId + 1;
        lastId += 1;


        for (User user: users) {
            if (rawUser.getLogin().equals(user.getLogin())) {
                throw new IllegalArgumentException("User with such login already exists");
            }
        }

        User user = new User(id, rawUser.getLogin(), rawUser.getPassword(), rawUser.getRole());
        users.add(user);
        return user;
    }

    public Role getRoleByCredentials(String headerCredentials) throws IllegalArgumentException {
        if (headerCredentials == null || headerCredentials.isBlank()) {
            throw new IllegalArgumentException("Authorization header is missing");
        }

        if (!headerCredentials.startsWith("Basic ")) {
            throw new IllegalArgumentException("Invalid authorization type");
        }
        String credentials;
        try {
            String base64Credentials = headerCredentials.substring(6).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 encoding", e);
        }

        // Разделение логина и пароля
        if (!credentials.contains(":")) {
            throw new IllegalArgumentException("Invalid credentials format");
        }
        String[] parts = credentials.split(":", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid credentials format");
        }
        String login = parts[0];
        String password = parts[1];

        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user.getRole();
            }
        }

        throw new IllegalArgumentException("Credentials not found");
    }
}
