package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.ChangeTaskHandlerException;
import server.exceptions.HandleException;
import server.view.RefreshHelper;
import shared.commandTools.*;
import shared.constants.GeneralConstantsPack;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ChangeTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws HandleException {
        try {
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
            ServerFacade serverFacade = ServerFacade.getInstance();
            CommandCreator commandCreator = CommandCreator.getInstance();
            Controller controller = Controller.getInstance();
            JsonBuilder jsonBuilder = JsonBuilder.getInstance();
            RefreshHelper refreshHelper = RefreshHelper.getInstance();
            Task task = TaskConverter.getInstance().convert(map);
            if (controller.getTask(task.getId()) == null) {
                Command command1 = commandCreator.createCommand(ClientCommandIdConstants.ERROR,
                        GeneralConstantsPack.DELETED_ERROR);
                serverFacade.getThread(command.getPort()).sendCommand(
                        jsonBuilder.createJsonString(command1));
                return;
            }
            if (controller.isTaskInJournal(task)) {
                Command command1 = commandCreator.createCommand(ClientCommandIdConstants.ERROR,
                        GeneralConstantsPack.EXIST_ERROR);
                serverFacade.getThread(command.getPort()).sendCommand(
                        jsonBuilder.createJsonString(command1));
                return;
            }
            if (controller.getTask(task.getId()).getStatus() == Status.COMPLETED &&
                    task.getStatus() == Status.DEFERRED) {
                Command command1 = commandCreator.createCommand(ClientCommandIdConstants.ERROR,
                        GeneralConstantsPack.COMPLETED_ERROR);
                serverFacade.getThread(command.getPort()).sendCommand(
                        jsonBuilder.createJsonString(command1));
                return;
            }
            controller.changeTask(task.getId(), task);
            refreshHelper.getMainWindowController().refresh();
            serverFacade.sendAll(commandCreator.createStringCommand(
                    ClientCommandIdConstants.GET_ALL_TASKS, controller.getAll()));
        } catch (IOException e) {
            throw new ChangeTaskHandlerException(e);
        }
    }

}
