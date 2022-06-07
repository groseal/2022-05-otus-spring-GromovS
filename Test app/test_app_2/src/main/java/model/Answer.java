package model;

import lombok.Data;

@Data
public class Answer {
    private boolean answerType;
    private boolean answerChosenByAnswerer;
    private boolean correctAnswer;
    private String answer;
    private int answerCost;
    private String userEnteredAnswer;

    @Override
    public String toString() {
        return "\n\t" + answer;
    }
}
