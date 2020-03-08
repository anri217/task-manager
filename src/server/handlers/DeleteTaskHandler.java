package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.DeleteTaskHandlerException;
import server.exceptions.HandleException;
import server.view.RefreshHelper;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteTaskHandler implements Handler {
    @Override
    public void handle(Command command) throws HandleException {
        Controller controller = Controller.getInstance();
        ArrayList<Integer> ids = (ArrayList<Integer>) command.getContent();
        controller.deleteTask(ids);
        RefreshHelper.getInstance().getMainWindowController().refresh();
        CommandCreator commandCreator = CommandCreator.getInstance();
        try {
            ServerFacade.getInstance().sendAll(commandCreator.createStringCommand(ClientCommandIdConstants.GET_ALL_TASKS, controller.getAll()));
        } catch (IOException e) {
            throw new DeleteTaskHandlerException(e);
        }
    }

}
