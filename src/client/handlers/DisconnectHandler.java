package client.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;
import shared.Handler;

public class DisconnectHandler implements Handler {
    @Override
    public void handle(Command command) throws JsonProcessingException {
        //todo действия, которые должны быть, если сервер прислан дисконнект - отключение (какое - надо уточнить).
    }
}
