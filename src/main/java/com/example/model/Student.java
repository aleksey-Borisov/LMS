package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int[] solvedProblems;

}
