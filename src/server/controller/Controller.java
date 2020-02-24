package server.controller;

import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;

import java.util.Collections;
import java.util.List;

/**
 * Class is using for change journal of tasks, to control journal
 *
 * @see Journal
 */

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

    /**
     * Function deleted all notifications for real journal is tasks
     */

    public void deleteAllNotification() {
        List<Task> tasks = journal.getAll();
        for (Task task : tasks) {
            if ((task.getStatus() != Status.CANCELED) && (task.getStatus() != Status.COMPLETED) && (task.getStatus() != Status.OVERDUE))
                notifier.deleteNotification(task);
        }
    }

    public Journal getJournal() {
        return journal;
    }

    /**
     * Getter function by id
     *
     * @return desired task
     */

    public Task getTask(int id) {
        return journal.getTask(id);
    }

    /**
     * Add function
     *
     * @param task - new task
     */

    public void addTask(Task task) {
        journal.addTask(task);
        if ((task.getStatus() != Status.CANCELED) && (task.getStatus() != Status.COMPLETED) && (task.getStatus() != Status.OVERDUE))
            notifier.createNotification(task);
    }

    /**
     * Delete function by id
     */

    public void deleteTask(int id) {
        if ((journal.getTask(id).getStatus() != Status.COMPLETED) && (journal.getTask(id).getStatus() != Status.OVERDUE) && (journal.getTask(id).getStatus() != Status.CANCELED))
            notifier.deleteNotification(journal.getTask(id));
        journal.deleteTask(id);
    }

    /**
     * Change function by id
     *
     * @param task2 - new task
     */

    public void changeTask(int id, Task task2) {
        if ((getTask(id).getStatus() != Status.CANCELED) && (getTask(id).getStatus() != Status.COMPLETED) && (getTask(id).getStatus() != Status.OVERDUE))
            notifier.deleteNotification(journal.getTask(id));
        journal.changeTask(id, task2);
        if ((task2.getStatus() != Status.COMPLETED) && (task2.getStatus() != Status.CANCELED)) {
            notifier.createNotification(journal.getTask(id));
        }
    }

    /**
     * Function for cancelling task by id
     */

    public void cancelTask(int id) {
        if ((getTask(id).getStatus() != Status.CANCELED) && (getTask(id).getStatus() != Status.COMPLETED) && (getTask(id).getStatus() != Status.OVERDUE))
            notifier.deleteNotification(journal.getTask(id));
        journal.getTask(id).setStatus(Status.CANCELED);
    }


    /**
     * @return unmodifiable list of all tasks
     */

    public List<Task> getAll() {
        return Collections.unmodifiableList(journal.getAll());
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }


    public boolean isTaskInJournal(Task task) {
        return journal.isTaskInJournal(task);
    }
}
