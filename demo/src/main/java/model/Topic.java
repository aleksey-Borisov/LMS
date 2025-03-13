package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic {
    private int id;
    private String Title;
    private String text;
    private int[] problems;
}
