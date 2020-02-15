package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;

public class GiveTaskToClientHandler implements Handler {
    @Override
    public void handle(Command command) throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        System.out.println(stringCommand); // todo заменить на отправку клиенту json строки stringCommand
    }
}
