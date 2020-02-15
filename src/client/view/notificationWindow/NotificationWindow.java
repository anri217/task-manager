package client.view.notificationWindow;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is class for opening window with notification
 */

public class NotificationWindow extends Application {
    public static void run(String[] args){Application.launch();}
    @Override
    public void start(Stage stage) throws IOException {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
