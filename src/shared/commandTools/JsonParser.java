package shared.commandTools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParser {
    private static JsonParser instance;

    public static JsonParser getInstance() {
        if (instance == null) {
            instance = new JsonParser();
        }
        return instance;
    }

    private JsonParser (){
    }

    public Command parseCommand(String stringCommand) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        Command command = (Command) mapper.readValue(stringCommand, Command.class);
        return command;
    }

}
