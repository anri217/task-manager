package client.handlers;

import client.ClientFacade;
import client.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;
import shared.Handler;

public class DisconnectHandler implements Handler {

    public void handle(Command command) throws JsonProcessingException {
        ClientFacade.getReaders().get((int)command.getContent()).setExit(false);
    }
}
