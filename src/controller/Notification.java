package controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Task;
import view.NotificationController;
import view.NotificationWindow;

import java.awt.*;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is class for working with notifications
 */

public class Notification extends TimerTask {
    private Timer timer;//todo if task has canceled recreate timer
    private Task task;

    public Timer getTimer() {
        return timer;
    }

    public Notification(Task task) {
        setTask(task);
        createTimer();
    }

    public void createTimer() {
        this.timer = new Timer();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    // todo looks like that you do not know single responsibility principle and do not delete debug code
    public Stage createStage() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println(width);
        System.out.println(height);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NotificationConst.NOTIFICATION_WINDOW_PATH));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(NotificationConst.NOTIFICATION_TITLE);
        stage.setX(width * 0.81);
        stage.setY(height * 0.78);
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

    public void run() {
        Platform.runLater(() -> {
            try {
                showNotification();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        timer.cancel();
    }

    public void cancelTimer() {
        timer.cancel();
    }

    public void startTask() {
        timer.schedule(this, Date.from(task.getPlannedDate().atZone(ZoneId.systemDefault()).toInstant()));
    }

}
