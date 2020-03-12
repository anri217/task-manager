package client.commandTools;

import client.handlers.*;
import client.handlers.HandlerException.HandleException;
import client.handlers.HandlerException.NotFoundHandlerException;
import shared.commandTools.ClientCommandIdConstants;
import shared.commandTools.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {

    private static CommandProcessor instance;

    public static synchronized CommandProcessor getInstance() {
        if (instance == null) {
            instance = new CommandProcessor();
        }
        return instance;
    }

    private Map<Integer, Handler> handlerMap;

    private CommandProcessor() {
        handlerMap = new HashMap<Integer, Handler>();
        handlerMap.put(ClientCommandIdConstants.GET_ALL_TASKS, new TakeAllTasksHandler());
        handlerMap.put(ClientCommandIdConstants.NOTIFICATION, new TakeNotificationHandler());
        handlerMap.put(ClientCommandIdConstants.ERROR, new ErrorHandler());
        handlerMap.put(ClientCommandIdConstants.DISCONNECT, new DisconnectHandler());
    }

    public void addHandler(Integer key, Handler handler) {
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws HandleException {
        int commandId = command.getCommandId();
        try {
            handlerMap.get(commandId).handle(command);
        } catch (NullPointerException e) {
            throw new NotFoundHandlerException(e);
        }
    }

}
