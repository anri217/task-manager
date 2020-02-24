package client.view.mainWindow;

import client.ClientFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;

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
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Command command = CommandCreator.getInstance().createCommand(5, ClientFacade.getPort());
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
    }
}