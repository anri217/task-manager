package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;
import shared.Handler;

public class ErrorHandler implements Handler {

    @Override
    public void handle(Command command) throws JsonProcessingException {
        switch (command.getCommandId()) {
            case 80:

        }
    }
}
