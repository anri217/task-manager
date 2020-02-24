package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import shared.TaskConverter;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FinishTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws Exception {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        task.setStatus(Status.COMPLETED);
        task.setDateOfDone(LocalDateTime.now());
        Controller.getInstance().changeTask(task.getId(), task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        String entry = createStringCommand();
        for (int port : clients.keySet()) {
            clients.get(port).sendCommand(entry);
        }
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());;
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);;
        return stringCommand;
    }
}
