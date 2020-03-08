package client.handlers;

import client.ClientFacade;
import client.handlers.HandlerException.DisconnectHandlerException;
import client.handlers.HandlerException.HandleException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.commandTools.Command;
import shared.commandTools.CommandSender;
import shared.constants.GeneralConstantsPack;

import java.io.IOException;
import java.util.Optional;

public class DisconnectHandler implements Handler {

    public void handle(Command command) throws HandleException {
        try {
            ClientFacade clientFacade = ClientFacade.getInstance();
            clientFacade.getListener().interrupt();
            clientFacade.getDis().close();
            CommandSender.getInstance().close();
            if (command.getContent().equals(GeneralConstantsPack.CONTENT_FOR_DISCONNECT)) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(GeneralConstantsPack.ALERT_NAME);
                    alert.setHeaderText(GeneralConstantsPack.ALERT_HEADER);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        System.exit(0);
                    }
                });
            }
        }
        catch (IOException e) {
            throw new DisconnectHandlerException(e);
        }
    }
}
