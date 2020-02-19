package client;

import client.handlers.*;
import shared.Command;
import shared.Handler;

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
        handlerMap = new HashMap<Integer, Handler>();
        handlerMap.put(0, new TakeAllTasksHandler());
        handlerMap.put(1, new TakeNotificationHandler());
        handlerMap.put(2, new TakeAllTasksFromStartHandler());
        handlerMap.put(99, new ErrorHandler());
        handlerMap.put(71, new DisconnectHandler());
    }

    public void addHandler(Integer key, Handler handler) {
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws Exception {
        int commandId = command.getCommandId();
        handlerMap.get(commandId).handle(command);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
