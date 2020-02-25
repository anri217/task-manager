package client.handlers;

import client.ClientFacade;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.Command;
import shared.CommandSender;
import shared.NamedConstants;

import java.io.IOException;
import java.util.Optional;

public class DisconnectHandler implements Handler {

    public void handle(Command command) throws IOException {
        ClientFacade clientFacade = ClientFacade.getInstance();
        clientFacade.getListener().interrupt();
        clientFacade.getDis().close();
        CommandSender.getInstance().close();
        if (command.getContent().equals(NamedConstants.WORD1)) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(NamedConstants.ALERT_NAME);
                alert.setHeaderText(NamedConstants.ALERT_HEADER);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.exit(0);
                }
            });
        }
    }
}
