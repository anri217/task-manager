package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is class for opening window with notification
 */

public class NotificationWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
