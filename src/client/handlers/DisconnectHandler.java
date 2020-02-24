package client.handlers;

import client.ClientFacade;
import shared.Command;
import shared.CommandSender;
import shared.Handler;

import java.io.IOException;

public class DisconnectHandler implements Handler {

    public void handle(Command command) throws IOException {
        ClientFacade.getListener().interrupt();
        ClientFacade.getDis().close();
        CommandSender.getInstance().close();
        if(((String) command.getContent()).equals("full")) {
            System.out.println("SERVER IS DOWN");
            System.exit(0);
        }
    }
}
