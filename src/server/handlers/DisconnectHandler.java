package server.handlers;

import server.MonoClientThread;
import server.ServerFacade;
import server.exceptions.DisconnectHandlerException;
import server.exceptions.HandleException;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;

import java.io.IOException;

public class DisconnectHandler implements Handler {

    @Override
    public void handle(Command command) throws HandleException {
        ServerFacade facade = ServerFacade.getInstance();
        int port = (int) command.getContent();
        MonoClientThread thread = facade.getThread(port);
        thread.setExit(false);
        try {
            thread.sendCommand(CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.DISCONNECT, port));
        } catch (IOException e) {
            throw new DisconnectHandlerException(e);
        }
        facade.removeThread(port);
    }

}
