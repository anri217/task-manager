package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.utils.Paths;
import server.controller.utils.PropertyParser;
import server.exceptions.PropertyParserInitException;
import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;
import shared.view.AlertShowing;

import java.io.IOException;
import java.net.Socket;

public class Client extends Application {
    public static void main(String[] args) {
        PropertyParser propertyParser = null;
        try {
            propertyParser = new PropertyParser(Paths.CLIENT);
        } catch (PropertyParserInitException e) {
            AlertShowing.showAlert(e.getMessage());
        }
        if (propertyParser != null) {
            try {
                ClientFacade clientFacade = new ClientFacade(new Socket("localhost",
                        Integer.parseInt(propertyParser.getProperty("port"))));
                clientFacade.connect();
                Application.launch(args);
            } catch (IOException e) {
                AlertShowing.showAlert(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainWindow/mainWindow.fxml"));
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
