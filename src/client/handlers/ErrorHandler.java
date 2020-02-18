package client.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import shared.Command;
import shared.Handler;
import shared.view.AlertShowing;

public class ErrorHandler implements Handler {

    @Override
    public void handle(Command command) throws JsonProcessingException {
        AlertShowing.showAlert((String)command.getContent());
        //System.out.println(this.message); //todo вместо вывода в консоль - появление окна с сообщением
    }
}
