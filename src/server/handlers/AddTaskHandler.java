package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.TaskConverter;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Handler;
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
        Task task =  TaskConverter.getInstance().convert((LinkedHashMap<String, Object>)command.getContent());
        Controller.getInstance().addTask(task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        HashMap<Integer, MonoClientThread>  clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        String entry = createStringCommand();
        for(int port : clients.keySet()) {
            clients.get(port).sendCommand(entry);
        }
        System.out.println(createStringCommand()); // todo вместо вывода в консоль - отправка клиенту той строки.
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        return stringCommand;
    }
}
