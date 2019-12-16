package model;

import java.time.ZoneId;
import java.util.*;

public class Notifier {
    private Map<Integer, Notification> notificationMap;
    private Timer timer;
    private TimerTask timerTask;

    public Notifier() {
        notificationMap = new LinkedHashMap<>();
    }

    /*public void createTask(Task task, Timer timerr){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Оповещение о задаче "+task.getName());
                timerr.cancel();
            }
        };
    }*/
    public void addNotification(Task task, Notification ntf) {
        notificationMap.putIfAbsent(task.getId(), ntf);
    }

    public void deleteNotification(Task task){
        notificationMap.remove(task.getId());
    }

    public void createNotification(Task task){
        Notification ntf = new Notification(task);
        addNotification(task, ntf);
        ntf.getTimer().schedule(ntf, Date.from(ntf.getDateAlert().atZone(ZoneId.systemDefault()).toInstant()));
       // ntf.startTask();
    }

    public TimerTask getNotification(int id) {
        return notificationMap.get(id);
    }

    /*public void createTimer(Task task, Notification alr) {
        Timer timerr = new Timer();
        createTask(task, timerr);
        //addNotification(task);
        timerr.schedule(notificationMap.get(task.getId()), alr.getDateAlert());
    }*/

    public void createTimer(Notification alr, TimerTask tTask) {
        timer = new Timer();
        timer.schedule(tTask, Date.from(alr.getDateAlert().atZone(ZoneId.systemDefault()).toInstant()));
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

