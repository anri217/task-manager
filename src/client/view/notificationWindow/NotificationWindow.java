package client.view.notificationWindow;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is class for opening window with notification
 */

public class NotificationWindow extends Application {
    @Override
    public void start(Stage stage) {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
