package client.handlers;

import shared.Command;
import shared.Handler;
import shared.view.AlertShowing;

public class ErrorHandler implements Handler {

    @Override
    public void handle(Command command) {
        AlertShowing.showAlert((String)command.getContent());
    }
}
