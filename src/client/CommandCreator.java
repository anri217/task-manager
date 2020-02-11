package client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.Command;
import shared.model.Task;

public class CommandCreator {

    public Command createCommand(int command_id, Object content){
        return new Command(command_id, content);
    }

}
