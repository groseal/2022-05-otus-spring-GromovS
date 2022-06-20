package repository;

import lombok.Data;
import model.Answer;
import model.Question;
import model.Task;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
@PropertySource("/application.properties")

public class TaskDaoImpl implements TaskDao {
    private final String repository;

    public TaskDaoImpl(String repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getListOfTasks() {
        List<Task> tasks = new ArrayList();

        try (InputStream in = getClass().getResourceAsStream(repository);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String taskString = reader.readLine();
            while (taskString != null) {
                tasks.add(getTaskFromString(taskString));
                taskString = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    private Task getTaskFromString(String taskStr) {
        Question question = new Question();
        List<Answer> answers = new ArrayList<>();
        Task task = new Task();
        String[] linesWithQuestions = taskStr.split(";");

        Question.QuestionType questionType = questionTypeDefinition(linesWithQuestions);
        question.setQuestionType(questionType);
        question.setSaveEnteredAnswer(questionType.equals(Question.QuestionType.OPEN_ENDED_QUESTION));
        question.setQuestion(linesWithQuestions[1]);
        question.setQuestionResolved(false);

        for (int i = 2; i < linesWithQuestions.length; i += 3) {
            Answer answer = new Answer();
            answer.setAnswerType(false);
            answer.setAnswerChosenByAnswerer(false);
            answer.setCorrectAnswer(linesWithQuestions[i].equals("1"));
            answer.setAnswer(linesWithQuestions[i + 1]);
            answer.setAnswerCost(Integer.parseInt(linesWithQuestions[i + 2]));
            answers.add(answer);
        }

        task.setQuestion(question);
        task.setAnswers(answers);
        return task;
    }

    private Question.QuestionType questionTypeDefinition(String[] linesWithQuestions) {
        char questionType = linesWithQuestions[0].charAt(linesWithQuestions[0].length() - 1);
        if (questionType == '1') {
            return Question.QuestionType.QUESTION_WITH_ONE_ANSVER;
        } else if (linesWithQuestions[0].equals("2")) {
            return Question.QuestionType.QUESTION_WITH_MULTIPLE_ANSVERS;
        } else {
            return Question.QuestionType.OPEN_ENDED_QUESTION;
        }
    }
}
