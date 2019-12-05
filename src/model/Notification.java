package model;

import java.util.Date;
import java.util.TimerTask;

public class Notification {
    private Date dateAlert;
    private String textAlert;
    private Notifier notifier;

    public Notification(Task task) {
        this.textAlert = task.getName() + " ." + task.getDescription();
        this.dateAlert = task.getDatePlan();
    }

    public Date getDateAlert() {
        return dateAlert;
    }

    public void setDateAlert(Date dateAlert) {
        this.dateAlert = dateAlert;
    }

    public String getTextAlert() {
        return textAlert;
    }

    public void setTextAlert(String textAlert) {
        this.textAlert = textAlert;
    }

}
