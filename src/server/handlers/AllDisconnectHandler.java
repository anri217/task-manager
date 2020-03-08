package server.handlers;

import server.ServerFacade;
import shared.ClientCommandIdConstants;
import shared.Command;
import shared.CommandCreator;
import shared.GeneralConstantsPack;

import java.io.IOException;

public class AllDisconnectHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        ServerFacade facade = ServerFacade.getInstance();
        facade.setAllExit(false);
        facade.sendAll(CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.DISCONNECT,
                GeneralConstantsPack.CONTENT_FOR_DISCONNECT));
    }
}
