package server;

import server.exceptions.HandleException;
import server.exceptions.NotFoundHandlerException;
import server.handlers.*;
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


    private Command command;
    private Map<Integer, Handler> handlerMap;

    private CommandProcessor() {
        handlerMap = new HashMap<>();
        handlerMap.put(0, new GiveTaskToClientHandler());
        handlerMap.put(1, new AddTaskHandler());
        handlerMap.put(2, new DeleteTaskHandler());
        handlerMap.put(3, new ChangeTaskHandler());
        handlerMap.put(4, new CancelTaskHandler());
        handlerMap.put(5, new DisconnectHandler());
        handlerMap.put(6, new AllDisconnectHandler());
        handlerMap.put(7, new FinishTaskHandler());
    }

    public void addHandler(Integer key, Handler handler) {
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws NotFoundHandlerException, HandleException {
        int commandId = command.getCommandId();
        try {
            handlerMap.get(commandId).handle(command);
        } catch (NullPointerException e) {
            throw new NotFoundHandlerException("Command with this commandId is not found");
        } catch (IOException e) {
            throw new HandleException(e);
        }
    }


    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
