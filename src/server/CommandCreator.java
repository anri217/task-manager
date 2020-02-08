package server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import shared.model.Task;

import java.util.HashMap;
import java.util.Map;

public class CommandCreator {
    @JsonIgnore
    private shared.model.Task task;
    private int command_id;
    private Map<Integer, Task> journal;
    private String command;

    public CommandCreator(){
        this.journal = new HashMap<Integer, Task>();
    }

    public CommandCreator(Map<Integer, Task> journal){
        this.journal = journal;
    }

    public void CreateJson() throws JsonProcessingException {
        setCommand(JsonBuilder.createJsonString(createCommand(this.command_id, this.journal)));
    }

    public void addTask(Task task){
        journal.put(task.getId(), task);
    }

    public Command createCommand(int command_id, Map<Integer, Task> journal){
        Command command = new Command(command_id, journal);
        return command;
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

}
