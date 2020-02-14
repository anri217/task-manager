package client.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;
import shared.Handler;

public class ErrorHandler implements Handler {
    private String message;

    @Override
    public void handle(Command command) throws JsonProcessingException {
        this.message = (String)command.getContent();
        System.out.println(this.message); //todo вместо вывода в консоль - появление окна с сообщением
    }
}
