package server;

import server.model.Task;

import java.util.HashMap;
import java.util.Map;

public class Command {
    private int command_id;
    private Map<Integer, Task> tasks;

    public Command(int command_id, Map<Integer, Task> tasks){
        setCommand_id(command_id);
        setTasks(tasks);
    }

    public Command(){
        tasks = new HashMap<Integer, Task>();
    }

    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    public int getCommand_id() {
        return command_id;
    }

    public void setTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }
}
