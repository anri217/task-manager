package controller;

import model.Journal;
import model.Notification;
import model.Notifier;
import model.Task;

import java.util.ArrayList;

public class Controller {
    private static Controller instance;
    private Journal journal;
    private Notifier notifier;


    private Controller() {
        journal = new Journal();
        notifier = new Notifier();
    }



    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }


    public void addTask(Task task) {
        journal.addTask(task);
        Notification notification = new Notification(task);

        notifier.addNotification(task);
        notifier.createTimer(task, notification);
    }

    public void deleteTask(int id) {
        journal.deleteTask(id);
        notifier.deleteNotification(journal.getTask(id));
    }

    public void changeTask(int id, Task task2) {
        notifier.deleteNotification(journal.getTask(id));
        journal.changeTask(id, task2);
        notifier.addNotification(journal.getTask(id));
    }

    public ArrayList<Task> getAll() {
        return journal.getAll();
    }
}
