package controller;

import model.Journal;
import model.Task;

public class Controller {
    private Journal journal;

    //singleton later...
    //notifier wait...

    Controller(Journal journal) {
        this.journal = journal;
    }

    public void addTask(Task task) {
        journal.addTask(task);
    }

    public void deleteTask(Task task) {
        journal.deleteTask(task);
    }

    public void changeTask(Task task1, Task task2) {
        task1.setStatus(task2.getStatus());
        task1.setDateOfDone(task2.getDateOfDone());
        task1.setDescription(task2.getDescription());
        task1.setName(task2.getName());
        task1.setPlannedDate(task2.getPlannedDate());
    }
}
