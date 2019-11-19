package controller;

import model.*;
import view.*;

import java.util.Date;

public class Controller {
    // todo create objects not from constructor is bad practice
    // also view in controller???
    View view = new JavaFXView();
    private JTasks jtasks = new JTasks();

    public void addTask(Task task) {
        jtasks.addTask(task);
    }

    public void deleteTask(Task task) {
        jtasks.deleteTask(task);
    }

    public void changeTask(Task task1, Task task2) {
        jtasks.setTask(task1.getName(), task2);
    }

    public void putAsideTask(Task task, Date newDate) {
        Task newTask = jtasks.getTaskByName(task.getName());
        newTask.setDatePlan(newDate);
        newTask.setStatus(Status.DEFERRED);
    }



}
