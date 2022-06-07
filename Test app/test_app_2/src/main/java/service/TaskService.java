package service;

import model.Task;

import java.util.List;

public interface TaskService {
    void displayListTasks(String path);

    List<Task> giveUserTasks(String path);
}
