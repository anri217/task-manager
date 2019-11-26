package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Notifier {
    private Map<Integer, TimerTask> notificationMap;
    private Timer timer;
    private TimerTask timerTask;

    public Notifier() {
        notificationMap = new LinkedHashMap<>();
    }

    public void createTask(Task task, Timer timerr) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Оповещение о задаче " + task.getName());
                timerr.cancel();
            }
        };
    }

    public void addNotification(Task task) {
        notificationMap.putIfAbsent(task.getId(), timerTask);
    }

    public void deleteNotification(Task task) {
        notificationMap.remove(task.getId());
    }


    public TimerTask getNotification(int id) {
        return notificationMap.get(id);
    }

    public void createTimer(Task task, Notification alr) {
        Timer timerr = new Timer();
        createTask(task, timerr);
        addNotification(task);
        timerr.schedule(notificationMap.get(task.getId()), alr.getDateAlert());
    }

    public void createTimer(Notification alr, TimerTask tTask) {
        timer = new Timer();
        timer.schedule(tTask, alr.getDateAlert());
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer usertimer) {
        timer = usertimer;
    }

    public void setTimerTask(TimerTask tTask) {
        timerTask = tTask;
    }

    public TimerTask getTimerTask() {
        return this.timerTask;
    }
}

