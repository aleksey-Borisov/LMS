package com.example.models;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Student {
    @NotNull(message = "ID cannot be null")
    private int id;

    @NotEmpty(message = "Login cannot be empty")
    @Size(max = 30, message = "Login must not exceed 30 characters")
    private String login;

    @Size(max = 100, message = "First Name must not exceed 100 characters")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Size(max = 100, message = "Last Name must not exceed 100 characters")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    private String phoneNumber;
    private List<Integer> solvedProblems;
}