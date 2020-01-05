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
    private Map<Integer, Notification> notificationMap;

    public Notifier() {
        notificationMap = new LinkedHashMap<>();
    }

    public void addNotification(Task task, Notification notification) {
        notificationMap.putIfAbsent(task.getId(), notification);
    }

    public void deleteNotification(Task task) {
        Notification notification = notificationMap.get(task.getId());
        notification.cancelTimer();
        notificationMap.remove(task.getId());
    }

    public void createNotification(Task task) {
        Notification notification = new Notification(task);
        addNotification(task, notification);
        notification.startTask();
    }

    public TimerTask getNotification(int id) {
        return notificationMap.get(id);
    }

}

