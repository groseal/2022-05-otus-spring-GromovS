package service;

import lombok.Data;
import model.Task;
import org.springframework.stereotype.Service;
import repository.TaskDaoImpl;

import java.util.List;

@Service
@Data
public class TaskServiceImpl implements TaskService {
    private final TaskDaoImpl taskDao;

    public TaskServiceImpl(TaskDaoImpl taskDaoImpl) {
        this.taskDao = taskDaoImpl;
    }

    @Override
    public void displayListTasks(String path) {
        System.out.println(taskDao.getListOfTasks());
    }

    @Override
    public List<Task> giveUserTasks(String path) {
        return taskDao.getListOfTasks();
    }
}
