package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int id;
    private String login;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private int[] SolvedPoblems;

}
