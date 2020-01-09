package controller;

import model.Task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * This is controller for Notification
 *
 * @see Notification
 */

public class Notifier {
    /**
     * Field for saving all notification in Notifier
     */
    private Map<Integer, Notification> notificationMap;

    /**
     * Constructor for creating Notifier
     */
    public Notifier() {
        notificationMap = new LinkedHashMap<>();
    }

    /**
     * Adding alert to map function
     *
     * @param task         which associated with notification
     * @param notification - created notification for task
     */
    public void addNotification(Task task, Notification notification) {
        notificationMap.putIfAbsent(task.getId(), notification);
    }

    /**
     * Delete notification from map and cancel timer function
     *
     * @param task which associated with deleted notification
     */
    public void deleteNotification(Task task) {
        Notification notification = notificationMap.get(task.getId());
        notification.cancelTimer();
        notificationMap.remove(task.getId());
    }

    /**
     * Create notification and start timer function
     *
     * @param task which associated with created notification
     */
    public void createNotification(Task task) {
        Notification notification = new Notification(task);
        addNotification(task, notification);
        notification.startTask();
    }

    /**
     * Getting notification from map function
     *
     * @param id - notification's  id
     * @return notification
     */
    public TimerTask getNotification(int id) {
        return notificationMap.get(id);
    }

}

