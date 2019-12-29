package controller;

import controller.Notification;
import model.Task;

import java.util.*;

public class Notifier {
    private Map<Integer, Notification> notificationMap;

    public Notifier() {
        notificationMap = new LinkedHashMap<>();
    }

    public void addNotification(Task task, Notification notification) {
        notificationMap.putIfAbsent(task.getId(), notification);
    }

    public void deleteNotification(Task task){
        Notification notification = notificationMap.get(task.getId());
        notification.cancelTimer();
        notificationMap.remove(task.getId());
    }

    public void createNotification(Task task){
        Notification notification = new Notification(task);
        addNotification(task, notification);
        notification.startTask();
      //  notification.getTimer().schedule(notification, Date.from(notification.getTask().getPlannedDate().atZone(ZoneId.systemDefault()).toInstant()));/*Date.from(notification.getDateAlert().atZone(ZoneId.systemDefault()).toInstant())*/
       // ntf.startTask();
    }

    public TimerTask getNotification(int id) {
        return notificationMap.get(id);
    }

}

