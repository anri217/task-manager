package client;

import server.controller.factories.TaskFactory;
import shared.Command;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GetAllClientHandler extends CommandProcessorHandler {
    private Command command;
    private List<LinkedHashMap<String, Object>> taskMap;
    private List<Task> tasks;

    public GetAllClientHandler(Command command){
        setCommand(command);
        tasks = new ArrayList<>();

    }

    @Override
    public boolean check(Command command) {
        if(command.getCommandId() == 0){
            taskMap = (List)command.getContent();
            for (int i = 0; i < taskMap.size(); i++){
                createTask(taskMap.get(i));
            }
            System.out.println(tasks);
        }
        return checkNext(command);
    }

    private void createTask(LinkedHashMap<String, Object> map){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plannedDate = LocalDateTime.parse((String)map.get("plannedDate"),formatter);
        TaskFactory taskFactory = new TaskFactory();
        Task task = taskFactory.createTask((int)map.get("id"), (String) map.get("name"),(String)map.get("description"),plannedDate,chooseStatus((String)map.get("status")));
        tasks.add(task);
        System.out.println(task);
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
}
