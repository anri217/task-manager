package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.TaskConverter;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class FinishTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        Task task = TaskConverter.getInstance().convert(map);
        task.setStatus(Status.COMPLETED);
        task.setDateOfDone(LocalDateTime.now());
        Controller controller = Controller.getInstance();
        controller.changeTask(task.getId(), task);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        CommandCreator commandCreator = CommandCreator.getInstance();
        ServerFacade.getInstance().sendAll(commandCreator.createStringCommand(0, controller.getAll()));
    }
}
