package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.*;
import shared.exceptions.PropertyParserInitException;
import shared.utils.Paths;
import shared.utils.PropertyParser;
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
                ClientFacade clientFacade = ClientFacade.getInstance();
                clientFacade.setSocket(new Socket(propertyParser.getProperty(NamedConstants.PROPERTY_NAME_HOST),
                        Integer.parseInt(propertyParser.getProperty(NamedConstants.PROPERTY_NAME_PORT))));
                clientFacade.connect();
                Application.launch(args);
            } catch (IOException e) {
                AlertShowing.showAlert(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(NamedConstants.PATH_TO_FXML));
        stage.setTitle(NamedConstants.WINDOW_NAME);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Command command = CommandCreator.getInstance().createCommand(ServerCommandIdConstants.DISCONNECT, ClientFacade.getInstance().getPort());
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
    }
}
