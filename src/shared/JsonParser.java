package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParser {
    private String stringCommand;

    public JsonParser(String stringCommand) {
        setStringCommand(stringCommand);
    }

    public Command parseCommand() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Command command = (Command) mapper.readValue(stringCommand, Command.class);
        return command;
        //todo изменить это использование полей command везде
    }

    public String getStringCommand() {
        return stringCommand;
    }

    public void setStringCommand(String stringCommand) {
        this.stringCommand = stringCommand;
    }
}
