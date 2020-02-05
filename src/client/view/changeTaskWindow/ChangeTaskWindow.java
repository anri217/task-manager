package client.view.changeTaskWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for opening window of changing task
 */

public class ChangeTaskWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("changeTaskWindow.fxml"));
        stage.setTitle("CHANGE TASK");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
