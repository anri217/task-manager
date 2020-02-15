package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import server.handlers.*;
import server.handlers.CancelTaskHandler;
import server.handlers.DeleteTaskHandler;
import server.view.mainWindow.MainWindowController;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Task;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private Command command;
    private Map<Integer, Handler> handlerMap;

    public CommandProcessor(){
        handlerMap = new HashMap<Integer, Handler>();
        handlerMap.put(0, new GiveTaskToClientHandler());
        handlerMap.put(1,new AddTaskHandler1());
        handlerMap.put(2, new DeleteTaskHandler());
        handlerMap.put(3,new CancelTaskHandler());
        handlerMap.put(4,new CancelTaskHandler());
        handlerMap.put(5, new DisconnectHandler());
    }

    public void addHandler(Integer key, Handler handler){
        handlerMap.put(key, handler);
    }

    public void processCommand(Command command) throws JsonProcessingException {
       int  commandId = command.getCommandId();
       handlerMap.get(commandId).handle(command);
        //это вся логика. + проверить на налл.
    }


    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
