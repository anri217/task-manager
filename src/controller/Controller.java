package controller;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Task getTask(int id) {
        return journal.getTask(id);
    }

    public void addTask(Task task) {
        journal.addTask(task);
        notifier.createNotification(task);
    }

    public void deleteTask(int id) {
        if (journal.getTask(id).getStatus() != Status.COMPLETED) notifier.deleteNotification(journal.getTask(id));
        journal.deleteTask(id);
    }

    public void changeTask(int id, Task task2) {
        if (getTask(id).getStatus() != Status.CANCELED) notifier.deleteNotification(journal.getTask(id));
        journal.changeTask(id, task2);
        if ((task2.getStatus() != Status.COMPLETED) && (task2.getStatus() != Status.CANCELED)) {
            notifier.createNotification(journal.getTask(id));
        }
    }

    public void cancelTask (int id){
        if (getTask(id).getStatus() != Status.CANCELED)
            notifier.deleteNotification(journal.getTask(id));
        journal.getTask(id).setStatus(Status.CANCELED);
    }

    public List<Task> getAll(){
        return Collections.unmodifiableList(journal.getAll());
    }
}
