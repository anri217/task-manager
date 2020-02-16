package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.TaskConverter;
import server.Writer;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DeleteTaskHandler implements Handler {
    @Override
    public void handle(Command command) throws IOException {
        ArrayList<Integer> ids = (ArrayList<Integer>) command.getContent();
        for (int i = 0; i < ids.size(); i++){
            Controller.getInstance().deleteTask(ids.get(i));
        }
        RefreshHelper.getInstance().getMainWindowController().refresh();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        String entry = createStringCommand();
        for(int port : clients.keySet()) {
            clients.get(port).sendCommand(entry);
        }
        System.out.println(createStringCommand()); // todo исправить на отправку команды клиенту
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);;
        return stringCommand;
    }
}
