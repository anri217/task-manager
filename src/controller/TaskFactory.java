package controller;

import model.Status;
import model.Task;

import java.util.Date;

public class TaskFactory implements Factory {
    public Task createTask() {
        return new Task();
    }

    public Task createTask(String name, String description, Date plannedDate, Status status) {
        return new Task(name, description, plannedDate, status);
    }
}
