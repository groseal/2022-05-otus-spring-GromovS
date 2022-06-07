package model;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    private String question;
    private List<String> answer;
}
