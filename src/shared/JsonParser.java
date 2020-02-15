package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import shared.Command;
import shared.model.Task;

import java.util.List;

public class JsonParser {
    private Command command;
    private String stringCommand;

    public JsonParser(String stringCommand){
        setStringCommand(stringCommand);
    }

    public void parseCommand() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        setCommand((Command)mapper.readValue(stringCommand, Command.class));

    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getStringCommand() {
        return stringCommand;
    }

    public void setStringCommand(String stringCommand) {
        this.stringCommand = stringCommand;
    }
}
