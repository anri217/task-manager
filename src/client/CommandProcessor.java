package client;

import client.handlers.*;
import client.handlers.HandlerException.HandleException;
import client.handlers.HandlerException.NotFoundHandlerException;
import shared.Command;

import java.io.IOException;
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
        handlerMap.put(0, new TakeAllTasksHandler());
        handlerMap.put(1, new TakeNotificationHandler());
        handlerMap.put(99, new ErrorHandler());
        handlerMap.put(71, new DisconnectHandler());
    }

    public void addHandler(Integer key, Handler handler) {
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws Exception {
        int commandId = command.getCommandId();
        try {
            handlerMap.get(commandId).handle(command);
        } catch (NullPointerException e) {
            throw new NotFoundHandlerException("Command with this commandId is not found");
        } catch (IOException e) {
            throw new HandleException(e);
        }
    }

}
