package com.example.models;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Problem {
    private int id;
    @NotEmpty(message = "title cannot be empty")
    @Size(max = 30, message = "title must not exceed 30 characters")
    private String title;
    @NotEmpty(message = "description cannot be empty")
    @Size(max = 3000, message = "description must not exceed 30 characters")
    private String description;
}
