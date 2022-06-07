package service;

import lombok.Data;
import repository.TaskDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class TaskServiceImpl implements TaskService {
    private final TaskDaoImpl taskDao;

    public TaskServiceImpl(TaskDaoImpl taskDaoImpl) {
        this.taskDao = taskDaoImpl;
    }

    @Override
    public void displayListQuestionsAndAnswers(String path) {
        InputStream in = getClass().getResourceAsStream(taskDao.getRepository());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        reader.lines().forEach(System.out::println);
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
