package server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.model.Journal;
import server.model.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandCreator {
    @JsonIgnore
    private server.model.Task task;
    private int command_id;
    private int task_count;
    @JsonIgnore
    private String command;
    private Map<Integer, Task> journal;

    public CommandCreator(){
        this.journal = new HashMap<Integer, Task>();
    }

    public CommandCreator(Map<Integer, Task> journal){
        this.journal = journal;
    }

  /*  public CommandCreator(server.model.Task task, int command_id, int task_count){
        setTask(task);
        setTask_count(task_count);
        setCommand_id(command_id);
        journal = new HashMap<Integer, Task>();
    }*/

    public void CreateJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.command = mapper.writeValueAsString(this);
        System.out.println(this.command);
    }

    public void addTask(Task task){
        journal.put(task.getId(), task);
        increaseTaskCount();
    }

    public void increaseTaskCount(){
        this.task_count++;
    }

    public void setJournal(Map<Integer, Task> journal) {
        this.journal = journal;
    }

    public Map<Integer, Task> getJournal() {
        return journal;
    }

    public void setCommand(String command){
        this.command=command;
    }

    public String getCommand(){
        return this.command;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask(){
        return this.task;
    }

    public void setCommand_id(int command_id){
        this.command_id = command_id;
    }

    public int getCommand_id(){
        return this.command_id;
    }

    public void setTask_count(int task_count){
        this.task_count = task_count;
    }

    public int getTask_count(){
        return this.task_count;
    }
}
