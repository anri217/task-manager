package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;

public class GetAllHandler extends CommandProcessorHandler {
    private Command command;

    public GetAllHandler(Command command){
        this.command = command;
    }

    @Override
    public boolean check(Command command) throws JsonProcessingException {
        if (command.getCommandId() == 0){
            Command newCommand = new CommandCreator().createCommand(0, Controller.getInstance().getAll());
            String stringCommand = new JsonBuilder().createJsonString(newCommand);
            System.out.println(stringCommand); //выслать команду клиенту
        }
        return checkNext(command);
    }
}
