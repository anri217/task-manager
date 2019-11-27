package controller;

import model.Journal;
import model.Notification;
import model.Notifier;
import model.Task;

public class Controller {
    private static Controller instanse;

    public static synchronized Controller getInstance() {
        if (instanse == null) {
            instanse = new Controller();
        }
        return instanse;
    }

    private Journal journal;
    private Notifier notifier = new Notifier();

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void addTask(Task task) {
        journal.addTask(task);
        Notification notification = new Notification(task);

        notifier.addNotification(task);
        notifier.createTimer(task, notification);
    }

    public void deleteTask(Task task) {
        journal.deleteTask(task);
        notifier.deleteNotification(task);
    }

    public void changeTask(Task task1, Task task2) {
        notifier.deleteNotification(task1);
        task1.setStatus(task2.getStatus());
        task1.setDateOfDone(task2.getDateOfDone());
        task1.setDescription(task2.getDescription());
        task1.setName(task2.getName());
        task1.setPlannedDate(task2.getPlannedDate());
        notifier.addNotification(task1);
    }
}
