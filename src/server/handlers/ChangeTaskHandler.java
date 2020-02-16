package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.MonoClientThread;
import server.ServerFacade;
import server.TaskConverter;
import server.Writer;
import server.controller.Controller;
import server.view.RefreshHelper;
import server.view.mainWindow.MainWindow;
import server.view.mainWindow.MainWindowController;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Task;

import java.io.IOException;
import java.sql.Ref;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ChangeTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        Controller.getInstance().changeTask(task.getId(), task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        String entry = createStringCommand();
        for(int port : clients.keySet()) {
            clients.get(port).sendCommand(entry);
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
