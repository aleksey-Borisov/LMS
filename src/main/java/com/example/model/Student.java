package com.example.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
@Getter
@Setter
public class Student {
    private int id;
    @NotNull
    private String login;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String phoneNumber;

    private int[] solvedProblems;

}
