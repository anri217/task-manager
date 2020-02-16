package client.handlers;

import client.view.notificationWindow.NotificationController;
import client.view.notificationWindow.NotificationWindow;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import server.TaskConverter;
import server.controller.NotificationConstants;
import shared.Command;
import shared.Handler;
import shared.model.Task;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashMap;

public class TakeNotificationHandler implements Handler {
    private Task task;

    @Override
    public void handle(Command command) throws Exception {
        LinkedHashMap<String, Object> taskMap = (LinkedHashMap<String, Object>) command.getContent();
        task = TaskConverter.getInstance().convert(taskMap);
        Platform.runLater(() -> {
            try {
                showNotification();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //System.out.println(this.task); //todo вместо вывода в консоль сделать появление в окне через метод showNotification
    }

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
        nc.setTask(this.task);
        nc.setLabel();
        return stage;
    }

    public void showNotification() throws Exception {
        NotificationWindow nw = new NotificationWindow();
        nw.start(createStage());
    }
}
