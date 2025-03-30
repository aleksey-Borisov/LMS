package com.example.models;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RawCourse {

    @NotEmpty(message = "title cannot be empty")
    @Size(max = 30, message = "title must not exceed 30 characters")
    private String title;
    @NotEmpty(message = "description cannot be empty")
    @Size(max = 3000, message = "description must not exceed 30 characters")
    private String description;
    private List<Integer> topics;    // Изменено с int[] на List<Integer>
    private List<Integer> students;  // Изменено с int[] на List<Integer>
}