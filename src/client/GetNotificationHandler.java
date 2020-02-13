package client;

import server.controller.factories.TaskFactory;
import shared.Command;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class GetNotificationHandler extends CommandProcessorHandler {
    private Command command;
    private LinkedHashMap<String, Object> map;
    private Task task;

    public  GetNotificationHandler(Command command){
        this.command = command;
    }

    @Override
    public boolean check(Command command) {
        if (command.getCommandId() == 1){
            map = (LinkedHashMap<String, Object>) command.getContent();
            createTask(map);
           // System.out.println(this.task);
        }
        return checkNext(command);
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

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
