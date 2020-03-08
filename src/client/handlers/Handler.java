package client.handlers;

import client.handlers.HandlerException.HandleException;
import shared.commandTools.Command;

public interface Handler {

    void handle(Command command) throws HandleException;
}
