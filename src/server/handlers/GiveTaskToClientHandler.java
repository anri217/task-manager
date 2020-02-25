package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;

import java.io.IOException;

public class GiveTaskToClientHandler implements Handler {
    @Override
    public void handle(Command command) throws IOException {
        ServerFacade.getInstance().getThread(command.getPort()).sendCommand(CommandCreator.getInstance().
                createStringCommand(0, Controller.getInstance().getAll()));
    }
}
