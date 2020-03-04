package server.handlers;

import server.MonoClientThread;
import server.ServerFacade;
import shared.ClientCommandIdConstants;
import shared.Command;
import shared.CommandCreator;
import shared.NamedConstants;

import java.io.IOException;
import java.util.HashMap;

public class AllDisconnectHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        ServerFacade facade = ServerFacade.getInstance();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) facade.getClientThreadMap();
        for (int port : clients.keySet()) { //здесь осталось все так же, просто ради одного хэндла добавлять метод в фасад
                                            //не имеет смысла
            clients.get(port).setExit(false);
            clients.get(port).sendCommand(CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.DISCONNECT, NamedConstants.WORD1));
        }
    }

}
