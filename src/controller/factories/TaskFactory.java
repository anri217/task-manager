package controller.factories;

import idgenerator.IdGenerator;
import model.Status;
import model.Task;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskFactory {

    public Task createTask(int id, String name, String description, LocalDateTime plannedDate, Status status) {
        return new Task(id, name, description, plannedDate, status);
    }

    public Task createTask(Task task) {
        return new Task(task);
    }
}
