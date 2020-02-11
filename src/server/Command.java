package server;

import shared.model.Task;

import java.util.HashMap;
import java.util.Map;

public class Command {
    private int command_id;
    private Object content;

    public Command(int command_id, Object content){
        setCommand_id(command_id);
        setContent(content);
    }

    public Command(){
        this.content = new Object();
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    public int getCommand_id() {
        return command_id;
    }
}
