package model;

import java.util.Timer;
import java.util.TimerTask;

public class Notifier {
    private Timer timer;
    private TimerTask timerTask;

    public Notifier(Notification alr) {
        timer = new Timer();
    }

    public void CreateTimer(Notification alr) {
        timer.schedule(timerTask, alr.getDateAlert());
    }

    public void CreateTimer(Notification alr, TimerTask tTask) {
        timer.schedule(tTask, alr.getDateAlert());
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer utimer) {
        timer = utimer;
    }

    public void setTimerTask(TimerTask tTask) {
        timerTask = tTask;
    }

    public TimerTask getTimerTask() {
        return this.timerTask;
    }
}
