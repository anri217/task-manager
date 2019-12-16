package controller;

import idgenerator.IdGenerator;
import model.Status;
import model.Task;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskFactory {
    public Task createTask() {
        return new Task(IdGenerator.getInstance().getId());
    }

    public Task createTask(String name, String description, LocalDateTime plannedDate, Status status) {
        return new Task(IdGenerator.getInstance().getId(), name, description, plannedDate, status);
    }
}
