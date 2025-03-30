package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

//import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
@Getter
@Setter
@AllArgsConstructor
public class RawTopic {

    @NotEmpty(message = "Ttle cannot be empty")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;
    @NotEmpty(message = "Text cannot be empty")
    @Size(max = 3000, message = "Text must not exceed 3000 characters")
    private String text;
    private List<Integer> problems;
}
