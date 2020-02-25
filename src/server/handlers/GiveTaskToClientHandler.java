package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;

import java.io.IOException;

public class GiveTaskToClientHandler implements Handler {
    @Override
    public void handle(Command command) throws IOException {
        ServerFacade.getInstance().getClients().get(command.getPort()).sendCommand(CommandCreator.getInstance().
                createStringCommand(0, Controller.getInstance().getAll()));
    }
}
