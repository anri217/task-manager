package server.handlers;

import server.ServerFacade;
import server.exceptions.AllDisconnectHandlerException;
import server.exceptions.HandleException;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;
import shared.constants.GeneralConstantsPack;

import java.io.IOException;

public class AllDisconnectHandler implements Handler {

    @Override
    public void handle(Command command) throws HandleException {
        ServerFacade facade = ServerFacade.getInstance();
        facade.setAllExit(false);
        try {
            facade.sendAll(CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.DISCONNECT,
                    GeneralConstantsPack.CONTENT_FOR_DISCONNECT));
        } catch (IOException e) {
            throw new AllDisconnectHandlerException(e);
        }
    }
}
