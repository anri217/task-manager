package server.view.mainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.view.RefreshHelper;

/**
 * Class of main window
 */

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        stage.setTitle("TASK MANAGER");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}