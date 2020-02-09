package server.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import shared.model.Task;
import client.view.notificationWindow.NotificationController;
import client.view.notificationWindow.NotificationWindow;

import java.awt.*;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for working with notifications
 */

public class Notification extends TimerTask {
    private Timer timer;
    private Task task;

    /**
     * Getting timer field function
     *
     * @return current timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Constructor for creating new notification
     *
     * @param task - task which associated with this notification
     */
    public Notification(Task task) {
        setTask(task);
        createTimer();
    }

    /**
     * timer field initializaton function
     */
    public void createTimer() {
        this.timer = new Timer();
    }

    /**
     * Getting task field function
     *
     * @return task, which associated with this notification
     */
    public Task getTask() {
        return task;
    }

    /**
     * task field change function
     *
     * @param task, which associated with this notification
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Сreate stage for Notification Window function
     *
     * @return created stage
     * @throws IOException
     */
    public Stage createStage() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NotificationConstants.NOTIFICATION_WINDOW_PATH));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(NotificationConstants.NOTIFICATION_TITLE);
        stage.setX(width * NotificationConstants.NOTIFICATION_WINDOW_WIDTH_COEFFICIENT);
        stage.setY(height * NotificationConstants.NOTIFICATION_WINDOW_HEIGHT_COEFFICIENT);
        stage.setScene(new Scene(loader.load()));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
            }
        });
        NotificationController nc = loader.<NotificationController>getController();
        nc.setNotification(this);
        nc.setLabel();
        return stage;
    }

    /**
     * Notification window launch function
     *
     * @throws Exception
     */
    public void showNotification() throws Exception {
        NotificationWindow nw = new NotificationWindow();
        nw.start(createStage());
    }

    @Override
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

    /**
     * timer kill function for current notification
     */
    public void cancelTimer() {
        timer.cancel();
    }

    /**
     * Start-up timer function
     */
    public void startTask() {
        timer.schedule(this, Date.from(task.getPlannedDate().atZone(ZoneId.systemDefault()).toInstant()));
    }

}
