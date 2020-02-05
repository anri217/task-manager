package client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommandCreator {
    private Task task;
    @JsonIgnore
    private String command;
    private int command_id;
    private int task_count;

    public CommandCreator(Task task, int task_count, int command_id){
        setTask(task);
        setTask_count(task_count);
        setCommand_id(command_id);
    }

    public void CreateJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.command = mapper.writeValueAsString(this);
        System.out.println(this.command);
    }

    public int getCommand_id() {
        return command_id;
    }

    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    public int getTask_count() {
        return task_count;
    }

    public void setTask_count(int task_count) {
        this.task_count = task_count;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
