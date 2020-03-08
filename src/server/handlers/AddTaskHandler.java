package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.ClientCommandIdConstants;
import shared.Command;
import shared.CommandCreator;
import shared.TaskConverter;
import shared.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;

public class AddTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        Task task = TaskConverter.getInstance().convert((LinkedHashMap<String, Object>) command.getContent());
        Controller controller = Controller.getInstance();
        controller.addTask(task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        String entry = CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS,
                controller.getAll());
        ServerFacade.getInstance().sendAll(entry);
    }

}
