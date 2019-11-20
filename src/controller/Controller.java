package controller;

import model.*;

import java.util.Date;

public class Controller {
    private Journal journal = new Journal();

    public void addTask(Task task) {
        journal.addTask(task);
    }

    public void deleteTask(Task task) {
        journal.deleteTask(task);
    }

    public void changeTask(Task task1, Task task2) {
        journal.setTask(task1.getName(), task2);
    }

    public void putAsideTask(Task task, Date newDate) {
        Task newTask = journal.getTaskByName(task.getName());
        newTask.setDatePlan(newDate);
        newTask.setStatus(Status.DEFERRED);
    }
}
