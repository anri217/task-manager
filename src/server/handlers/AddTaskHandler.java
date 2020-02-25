package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.exceptions.HandleException;
import server.exceptions.NotFoundHandlerException;
import shared.TaskConverter;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AddTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        Task task = TaskConverter.getInstance().convert((LinkedHashMap<String, Object>) command.getContent());
        Controller.getInstance().addTask(task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        String entry = CommandCreator.getInstance().createStringCommand(0, Controller.getInstance().getAll());
        for (int port : clients.keySet()) {
            clients.get(port).sendCommand(entry);
        }
    }

}
