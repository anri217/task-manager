package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.TaskConverter;
import server.controller.Controller;
import shared.Handler;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Task;

import java.util.LinkedHashMap;

public class AddTaskHandler1 implements Handler {

    @Override
    public void handle(Command command) throws JsonProcessingException {
        TaskConverter taskConverter = new TaskConverter();
        Task task =  taskConverter.convert((LinkedHashMap<String, Object>)command.getContent());
        Controller.getInstance().addTask(task);
        System.out.println(createStringCommand()); // todo вместо вывода в консоль - отправка клиенту той строки.
    }

    private String createStringCommand() throws JsonProcessingException {
        CommandCreator commandCreator = new CommandCreator();
        Command newCommand = commandCreator.createCommand(0, Controller.getInstance().getAll());
        String stringJson = JsonBuilder.createJsonString(newCommand);
        return stringJson;
    }
}
