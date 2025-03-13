package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private int id;
    private String Title;
    private String Description;
    private String[] topics;
    private int[] students;

}
