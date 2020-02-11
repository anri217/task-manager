package server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import shared.model.Task;

import java.util.HashMap;
import java.util.Map;

public class CommandCreator {

    public Command createCommand(int command_id, Object content){
        return new Command(command_id, content);
    }

}
