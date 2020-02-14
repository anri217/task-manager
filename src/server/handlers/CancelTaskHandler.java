package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.TaskConverter;
import server.controller.Controller;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Task;

import java.util.LinkedHashMap;

public class CancelTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws JsonProcessingException {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
        TaskConverter taskConverter = new TaskConverter();
        Task task = taskConverter.convert(map);
        Controller.getInstance().cancelTask(task.getId());
        System.out.println("Сформированная команда после изменения задачи: "+createStringCommand()); // todo вместо вывода в консоль отправка клиенту строки
    }

    private String createStringCommand() throws JsonProcessingException {
        CommandCreator commandCreator = new CommandCreator();
        Command newCommand = commandCreator.createCommand(0, Controller.getInstance().getAll());
        String stringJson = JsonBuilder.createJsonString(newCommand);
        return stringJson;
    }
}
