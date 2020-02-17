package client.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import shared.Command;
import shared.Handler;

public class ErrorHandler implements Handler {
    private String message;

    @Override
    public void handle(Command command) throws JsonProcessingException {
        this.message = (String)command.getContent();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText(this.message);
            alert.showAndWait();
        });
        System.out.println(this.message); //todo вместо вывода в консоль - появление окна с сообщением
    }
}
