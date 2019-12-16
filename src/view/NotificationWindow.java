package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NotificationWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("notificationWindow.fxml"));
        stage.setTitle("ADD TASK");
        stage.setResizable(false);
        stage.setScene(new Scene(root));*/
        stage.show();
    }
}
