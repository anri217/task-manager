package server.handlers;

import server.exceptions.HandleException;
import server.exceptions.NotFoundHandlerException;
import shared.Command;

import java.io.IOException;

public interface Handler {

    public void handle(Command command) throws IOException;
}
