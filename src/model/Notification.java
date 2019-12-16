package model;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.AddTaskWindow;
import view.NotificationController;
import view.NotificationWindow;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Notification extends TimerTask{
    private LocalDateTime dateAlert;
    private String textAlert;
    private Notifier notifier;
    private Timer timer;
    private Task task;

    public Timer getTimer(){
        return timer;
    }

    public Notification(Task task) {
        this.textAlert = task.getName() + " ." + task.getDescription();
        this.dateAlert = task.getPlannedDate();
        this.task = task;
        this.timer = new Timer();
    }

    public Stage createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/notificationWindow.fxml"));
        Stage stage = new Stage( StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        NotificationController nc = loader.<NotificationController>getController();
        nc.setNotification(this);
        nc.setLabel();
        return stage;
    }

    public void showNotification() throws Exception {
        NotificationWindow nw = new NotificationWindow();
        nw.start(createStage());
    }
    public void run(){
        Platform.runLater(()-> {
            try {
                showNotification();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        timer.cancel();
    }

    public void startTask(){
        timer.schedule(this, Date.from(dateAlert.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public LocalDateTime getDateAlert() {
        return dateAlert;
    }

    public void setDateAlert(LocalDateTime dateAlert) {
        this.dateAlert = dateAlert;
    }

    public String getTextAlert() {
        return textAlert;
    }

    public void setTextAlert(String textAlert) {
        this.textAlert = textAlert;
    }

}
