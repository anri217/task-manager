package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;

import java.io.IOException;
import java.util.HashMap;

public class GiveTaskToClientHandler implements Handler {
    @Override
    public void handle(Command command) throws IOException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        for(int port : clients.keySet()) {
            clients.get(port).sendCommand(stringCommand);
        }
        System.out.println(stringCommand); // todo заменить на отправку клиенту json строки stringCommand
    }
}
