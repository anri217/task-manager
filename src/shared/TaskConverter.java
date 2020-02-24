package shared;

import shared.factories.TaskFactory;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
//todo сделать красиво без linkedmap и нормально оформить ретерны
public class TaskConverter {
    private Map<String, Object> map;
    private static TaskConverter instance;

    private TaskConverter() {
        this.map = new LinkedHashMap<String, Object>();
    }

    public static TaskConverter getInstance() {
        if (instance == null) {
            instance = new TaskConverter();
        }
        return instance;
    }

    public Task convert(Map<String, Object> taskMap) {
        this.map = taskMap;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plannedDate = LocalDateTime.parse((String) map.get("plannedDate"), formatter);
        TaskFactory taskFactory = new TaskFactory();
        Task task = taskFactory.createTask((int) map.get("id"), (String) map.get("name"),
                (String) map.get("description"), plannedDate, chooseStatus((String) map.get("status")));
        return task;
    }

//todo через switch case
    private Status chooseStatus(String statusString) {
        Status status = null;
        switch (statusString) {
            case ("PLANNED"):
                status = Status.PLANNED;
                break;
            case ("COMPLETED"):
                status = Status.COMPLETED;
                break;
            case ("OVERDUE"):
                status = Status.OVERDUE;
                break;
            case ("CANCELED"):
                status = Status.CANCELED;
                break;
            case ("DEFERRED"):
                status = Status.DEFERRED;
                break;
        };
        return status;
    }
}
