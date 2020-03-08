package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.FinishTaskHandlerException;
import server.exceptions.HandleException;
import server.view.RefreshHelper;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;
import shared.commandTools.TaskConverter;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class FinishTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws HandleException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        task.setStatus(Status.COMPLETED);
        task.setDateOfDone(LocalDateTime.now());
        Controller controller = Controller.getInstance();
        controller.changeTask(task.getId(), task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        CommandCreator commandCreator = CommandCreator.getInstance();
        try {
            ServerFacade.getInstance().sendAll(commandCreator.createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS, controller.getAll()));
        } catch (IOException e) {
            throw new FinishTaskHandlerException(e);
        }
    }
}
