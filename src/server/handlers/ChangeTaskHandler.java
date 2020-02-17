package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.TaskConverter;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ChangeTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        if (Controller.getInstance().getTask(task.getId()) == null) {
            Command command1 = CommandCreator.getInstance().createCommand(99, "THIS TASK HAS ALREADY DELETED");
            ServerFacade.getInstance().getClients().get(command.getPort()).sendCommand(JsonBuilder.getInstance().createJsonString(command1));
        }
        else if(Controller.getInstance().isTaskInJournal(task)) {
            Command command1 = CommandCreator.getInstance().createCommand(99, "THIS TASK HAS ALREADY EXIST");
            ServerFacade.getInstance().getClients().get(command.getPort()).sendCommand(JsonBuilder.getInstance().createJsonString(command1));
        }
        else {
            Controller.getInstance().changeTask(task.getId(), task);
            RefreshHelper.getInstance().getMainWindowController().refresh();
            HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
            String entry = createStringCommand();
            for (int port : clients.keySet()) {
                clients.get(port).sendCommand(entry);
            }
        }
        //System.out.println(createStringCommand()); // todo заменить на отправку строки с командой клиенту.
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());;
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);;
        return stringCommand;
    }
}
