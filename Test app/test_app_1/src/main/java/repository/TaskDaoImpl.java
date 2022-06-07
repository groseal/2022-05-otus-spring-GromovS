package repository;

import lombok.Data;

@Data
public class TaskDaoImpl implements TaskDao {
    private final String repository;

    public TaskDaoImpl(String repository) {
        this.repository = repository;
    }
}
