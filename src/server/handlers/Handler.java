package server.handlers;

import server.exceptions.HandleException;
import shared.commandTools.Command;

public interface Handler {

    public void handle(Command command) throws HandleException;
}
