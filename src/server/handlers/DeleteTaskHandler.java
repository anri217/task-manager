package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.ClientCommandIdConstants;
import shared.Command;
import shared.CommandCreator;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteTaskHandler implements Handler {
    @Override
    public void handle(Command command) throws IOException {
        Controller controller = Controller.getInstance();
        ArrayList<Integer> ids = (ArrayList<Integer>) command.getContent();
        for (Integer id : ids) {
            controller.deleteTask(id);
        }
        RefreshHelper.getInstance().getMainWindowController().refresh();
        CommandCreator commandCreator = CommandCreator.getInstance();
        ServerFacade.getInstance().sendAll(commandCreator.createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS, controller.getAll()));
    }

}
