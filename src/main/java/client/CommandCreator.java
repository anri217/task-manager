package client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommandCreator {
    private String task_name;
    private String task_discription;
    private LocalDateTime task_PlannedDate;
    private String task_Status;
    private int task_id;
    private LocalDateTime task_DateOfDone;
    @JsonIgnore
    private String command;
    private int command_id;
    private int task_count;

    public CommandCreator(int command_id,int task_count, int task_id, String task_name, String task_discription, LocalDateTime task_PlannedDate, String task_Status){
        setCommand_id(command_id);
        setTask_count(task_count);
        setTask_id(task_id);
        setTask_name(task_name);
        setTask_discription(task_discription);
        setTask_DateOfDone(task_DateOfDone);
        setTask_PlannedDate(task_PlannedDate);
        setTask_Status(task_Status);
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

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_discription(String task_discription) {
        this.task_discription = task_discription;
    }

    public String getTask_discription() {
        return task_discription;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_DateOfDone(LocalDateTime task_DateOfDone) {
        this.task_DateOfDone = task_DateOfDone;
    }

    public LocalDateTime getTask_DateOfDone() {
        return task_DateOfDone;
    }

    public void setTask_PlannedDate(LocalDateTime task_PlannedDate) {
        this.task_PlannedDate = task_PlannedDate;
    }

    public LocalDateTime getTask_PlannedDate() {
        return task_PlannedDate;
    }

    public void setTask_Status(String task_Status) {
        this.task_Status = task_Status;
    }

    public String getTask_Status() {
        return task_Status;
    }
}
