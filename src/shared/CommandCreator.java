package shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;

public class CommandCreator {
    private static CommandCreator instance;

    private CommandCreator() {
    }

    public static synchronized CommandCreator getInstance() {
        if (instance == null) {
            instance = new CommandCreator();
        }
        return instance;
    }

    public Command createCommand(int commandId, Object content) {
        return new Command(commandId, content);
    }

    public Command createCommand(int commandId, Object content, int port) {
        return new Command(commandId, content, port);
    }

    public String createStringCommand(int commandId, Object content) throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(commandId, content);
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        return stringCommand;
    }

}
