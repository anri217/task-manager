package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.GiveTaskToClientHandlerException;
import server.exceptions.HandleException;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;

import java.io.IOException;

public class GiveTaskToClientHandler implements Handler {
    @Override
    public void handle(Command command) throws HandleException {
        try {
            ServerFacade.getInstance().getThread(command.getPort()).sendCommand(CommandCreator.getInstance().
                    createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS, Controller.getInstance().getAll()));
        } catch (IOException e) {
            throw new GiveTaskToClientHandlerException(e);
        }
    }
}
