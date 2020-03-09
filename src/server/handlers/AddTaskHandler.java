package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.AddTaskHandlerException;
import server.exceptions.HandleException;
import server.view.RefreshHelper;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;
import shared.commandTools.TaskConverter;
import shared.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;

public class AddTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws HandleException {
        try {
            Task task = TaskConverter.getInstance().convert((LinkedHashMap<String, Object>) command.getContent());
            Controller controller = Controller.getInstance();
            controller.addTask(task);
            RefreshHelper.getInstance().getMainWindowController().refresh();
            String entry = CommandCreator.getInstance().createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS,
                    controller.getAll());
            ServerFacade.getInstance().sendAll(entry);
        } catch (IOException e) {
            throw new AddTaskHandlerException(e);
        }

    }

}
