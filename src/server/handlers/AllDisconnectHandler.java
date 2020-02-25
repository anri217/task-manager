package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;

import java.io.IOException;
import java.util.HashMap;

public class AllDisconnectHandler implements Handler {
    private int port;

    @Override
    public void handle(Command command) throws IOException {
        ServerFacade facade = ServerFacade.getInstance();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) facade.getClients();
        for (int port : clients.keySet()) {
            clients.get(port).setExit(false);
        }
        for (int port : clients.keySet()) {
            clients.get(port).sendCommand(CommandCreator.getInstance().createStringCommand(71, "full"));
        }
    }

}
