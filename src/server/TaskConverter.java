package server;

import server.controller.factories.TaskFactory;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public Task convert(LinkedHashMap<String, Object> taskMap) {
        this.map = taskMap;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plannedDate = LocalDateTime.parse((String) map.get("plannedDate"), formatter);
        TaskFactory taskFactory = new TaskFactory();
        return (taskFactory.createTask((int) map.get("id"), (String) map.get("name"), (String) map.get("description"), plannedDate, chooseStatus((String) map.get("status"))));
    }

    private Status chooseStatus(String status) {
        if (status.equals("PLANNED"))
            return Status.PLANNED;
        else {
            if (status.equals("COMPLETED")) return Status.COMPLETED;
            else {
                if (status.equals("OVERDUE")) return Status.OVERDUE;
                else {
                    if (status.equals("CANCELED")) return Status.CANCELED;
                    else return Status.DEFERRED;
                }
            }
        }
    }
}
