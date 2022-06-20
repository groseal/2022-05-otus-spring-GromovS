import config.AppConfig;
import model.Answer;
import model.Question;
import model.Task;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repository.TaskDaoImpl;
import service.TaskService;

import java.util.List;
import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        TaskDaoImpl taskDao = context.getBean(TaskDaoImpl.class);
        TaskService service = context.getBean(TaskService.class);

        Scanner in = new Scanner(System.in);
        System.out.println("Enter last name and first name.");
        String name = in.nextLine();

        List<Task> tasks = service.getListOfTasks(taskDao.getRepository());

        int pointsReceived = 0;
        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("Enter answers separated by commas:");
            String answer = in.nextLine();
            pointsReceived += chowManyPointsPerAnswer(task, answer);
            System.out.println(pointsReceived);
        }
        System.out.println(name + ", your result " + pointsReceived + " points.");
    }

    private static int chowManyPointsPerAnswer(Task task, String answerUser) {
        List<Answer> answers = task.getAnswers();
        if (task.getQuestion().getQuestionType().equals(Question.QuestionType.QUESTION_WITH_ONE_ANSVER)) {
            for (Answer answer : answers) {
                if (answer.getAnswer().equalsIgnoreCase(answerUser))
                    return answer.getAnswerCost();
            }
        }
        if (task.getQuestion().getQuestionType().equals(Question.QuestionType.QUESTION_WITH_MULTIPLE_ANSVERS)) {
            int scoreForAnswers = 0;
            String[] arrayUserAnswers = answerUser.split(",");
            for (Answer answer : answers) {
                for (int i = 0; i < arrayUserAnswers.length; i++) {
                    if (answer.getAnswer().equalsIgnoreCase(arrayUserAnswers[i].trim())) {
                        scoreForAnswers += answer.getAnswerCost();
                    }
                }
            }
            return scoreForAnswers;
        }
        return 0;
    }
}
