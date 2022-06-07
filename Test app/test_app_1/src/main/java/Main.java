import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.TaskDaoImpl;
import service.TaskService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TaskService service = context.getBean(TaskService.class);
        TaskDaoImpl taskDao = context.getBean(TaskDaoImpl.class);

        service.displayListQuestionsAndAnswers(taskDao.getRepository());
        context.close();

    }
}
