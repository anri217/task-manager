package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
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
            clients.get(port).sendCommand(createStringCommand());
        }
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(71, "full");
        JsonBuilder.getInstance().createJsonString(newCommand);
        return JsonBuilder.getInstance().createJsonString(newCommand);
    }
}
