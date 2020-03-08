package client.handlers;

import shared.commandTools.Command;
import shared.view.AlertShowing;

public class ErrorHandler implements Handler {

    @Override
    public void handle(Command command) {
        AlertShowing.showAlert((String) command.getContent());
    }
}
