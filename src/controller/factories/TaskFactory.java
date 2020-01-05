package controller.factories;

import model.Status;
import model.Task;

import java.time.LocalDateTime;

/**
 * This is the Factory pattern creating tasks
 *
 * @see Task
 */

public class TaskFactory {

    public Task createTask(int id, String name, String description, LocalDateTime plannedDate, Status status) {
        return new Task(id, name, description, plannedDate, status);
    }

    public Task createTask(Task task) {
        return new Task(task);
    }
}
