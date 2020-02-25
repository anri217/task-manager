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

public class DisconnectHandler implements Handler {
    private int port;

    @Override
    public void handle(Command command) throws IOException {
        ServerFacade facade = ServerFacade.getInstance();
        port = (int) command.getContent();
        HashMap<Integer, MonoClientThread> map = (HashMap<Integer, MonoClientThread>) facade.getClients();
        map.get(port).setExit(false);
        map.get(port).sendCommand(CommandCreator.getInstance().createStringCommand(71, port));
        map.remove(port);
        ServerFacade.getInstance().setClients(map);
    }

}
