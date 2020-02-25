package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.*;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ChangeTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        if (Controller.getInstance().getTask(task.getId()) == null) {
            Command command1 = CommandCreator.getInstance().createCommand(99, NamedConstants.ERROR1);
            ServerFacade.getInstance().getThread(command.getPort()).sendCommand(JsonBuilder.getInstance().createJsonString(command1));
        } else if (Controller.getInstance().isTaskInJournal(task)) {
            Command command1 = CommandCreator.getInstance().createCommand(99, NamedConstants.ERROR2);
            ServerFacade.getInstance().getThread(command.getPort()).sendCommand(JsonBuilder.getInstance().createJsonString(command1));
        } else if (Controller.getInstance().getTask(task.getId()).getStatus() == Status.COMPLETED &&
                task.getStatus() == Status.DEFERRED) {
            Command command1 = CommandCreator.getInstance().createCommand(99, NamedConstants.ERROR3);
            ServerFacade.getInstance().getThread(command.getPort()).sendCommand(JsonBuilder.getInstance().createJsonString(command1));
        } else {
            Controller.getInstance().changeTask(task.getId(), task);
            RefreshHelper.getInstance().getMainWindowController().refresh();
            ServerFacade.getInstance().sendAll(CommandCreator.getInstance().createStringCommand(0, Controller.getInstance().getAll()));
        }
    }

}
