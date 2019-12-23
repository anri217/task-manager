package controller;

import model.*;

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
        if (journal.getTask(id).getStatus() != Status.FINISHED) notifier.deleteNotification(journal.getTask(id));
        journal.deleteTask(id);
    }

    public void changeTask(int id, Task task2) {
        notifier.deleteNotification(journal.getTask(id));
        journal.changeTask(id, task2);
        if (task2.getStatus() != Status.FINISHED) {
            notifier.createNotification(task2);
        }
    }

    public ArrayList<Task> getAll(){
        return journal.getAll();
    }
}
