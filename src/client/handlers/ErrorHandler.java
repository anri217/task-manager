package client.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;
import shared.Handler;
import shared.view.AlertShowing;

public class ErrorHandler implements Handler {

    @Override
    public void handle(Command command) throws JsonProcessingException {
        AlertShowing.showAlert((String)command.getContent());
    }
}
