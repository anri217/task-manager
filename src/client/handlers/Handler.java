package client.handlers;

import shared.Command;

import java.io.IOException;

public interface Handler {

    void handle(Command command) throws IOException;
}
