package model;

import java.util.Date;

public class Alert {
    private Date dateAlert;
    private String textAlert;

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
