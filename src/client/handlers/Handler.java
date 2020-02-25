package client.handlers;

import shared.Command;

import java.io.IOException;

public interface Handler {

    public void handle(Command command) throws IOException;
}
