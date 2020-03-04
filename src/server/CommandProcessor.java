package server;

import server.exceptions.HandleException;
import server.exceptions.NotFoundHandlerException;
import server.handlers.*;
import shared.Command;
import shared.ServerCommandIdConstants;

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
        handlerMap.put(ServerCommandIdConstants.GET_ALL_TASKS, new GiveTaskToClientHandler());
        handlerMap.put(ServerCommandIdConstants.ADD_TASK, new AddTaskHandler());
        handlerMap.put(ServerCommandIdConstants.DELETE_TASK, new DeleteTaskHandler());
        handlerMap.put(ServerCommandIdConstants.CHANGE_TASK, new ChangeTaskHandler());
        handlerMap.put(ServerCommandIdConstants.CANCEL_TASK, new CancelTaskHandler());
        handlerMap.put(ServerCommandIdConstants.DISCONNECT, new DisconnectHandler());
        handlerMap.put(ServerCommandIdConstants.ALL_DISCONNECT, new AllDisconnectHandler());
        handlerMap.put(ServerCommandIdConstants.FINISH_TASK, new FinishTaskHandler());
    }

    public void addHandler(Integer key, Handler handler) {
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws NotFoundHandlerException, HandleException {
        int commandId = command.getCommandId();
        try {
            handlerMap.get(commandId).handle(command);
        } catch (NullPointerException e) {
            throw new NotFoundHandlerException(HandlerExceptionConstants.NOT_FOUND_HANDLER_EXCEPTION);
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
