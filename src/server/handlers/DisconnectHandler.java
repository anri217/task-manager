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

public class DisconnectHandler implements Handler {
    private int port;

    @Override
    public void handle(Command command) throws IOException {
        port = (int) command.getContent();
        HashMap<Integer, MonoClientThread> map = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        ServerFacade.getInstance().getClients().get(port).setExit(false);
        ServerFacade.getInstance().getClients().get(port).sendCommand(createStringCommand());
        map.remove(port);
        ServerFacade.getInstance().setClients(map);
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(71, port);
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        return stringCommand;
    }
}
