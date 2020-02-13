package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import server.controller.factories.TaskFactory;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class CancelTaskHandler extends CommandProcessorHandler {
    private Command command;
    private Task task;

    public CancelTaskHandler(Command command){
        this.command = command;
    }

    @Override
    public boolean check(Command command) throws JsonProcessingException {
        if(command.getCommandId() == 4){
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) command.getContent();
            createTask(map);
            Controller.getInstance().cancelTask(task.getId());
            System.out.println(createStringCommand());
        }
        return checkNext(command);
    }

    private String createStringCommand() throws JsonProcessingException {
        CommandCreator commandCreator = new CommandCreator();
        Command newCommand = commandCreator.createCommand(0, Controller.getInstance().getAll());
        String stringJson = JsonBuilder.createJsonString(newCommand);
        return stringJson;
    }

    private void createTask(LinkedHashMap<String, Object> map){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plannedDate = LocalDateTime.parse((String)map.get("plannedDate"),formatter);
        TaskFactory taskFactory = new TaskFactory();
        this.task = taskFactory.createTask((int)map.get("id"), (String) map.get("name"),(String)map.get("description"),plannedDate,chooseStatus((String)map.get("status")));
    }

    private Status chooseStatus(String status){
        if (status.equals("PLANNED"))
            return Status.PLANNED;
        else{
            if (status.equals("COMPLETED")) return Status.COMPLETED;
            else{
                if(status.equals("OVERDUE")) return Status.OVERDUE;
                else{
                    if (status.equals("CANCELED")) return Status.CANCELED;
                    else return Status.DEFERRED;
                }
            }
        }
    }
}
