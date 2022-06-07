package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import repository.TaskDao;
import repository.TaskDaoImpl;
import service.TaskService;
import service.TaskServiceImpl;

@PropertySource("/application.properties")
@Configuration
public class AppConfig {

    @Bean
    public TaskDao taskDao(@Value("${tasks.path}") String tasksPath) {
        return new TaskDaoImpl(tasksPath);
    }

    @Bean
    public TaskService taskService(TaskDaoImpl taskDao){
        return new TaskServiceImpl(taskDao);
    }
}
