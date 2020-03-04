package server.handlers;

import server.ServerFacade;
import shared.ClientCommandIdConstants;
import shared.Command;
import shared.CommandCreator;

import java.io.IOException;

public class DisconnectHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        ServerFacade facade = ServerFacade.getInstance();
        int port = (int) command.getContent();
        facade.getThread(port).setExit(false);
        facade.getThread(port).sendCommand(CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.DISCONNECT, port));
        facade.removeThread(port);
    }

}
