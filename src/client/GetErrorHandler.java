package client;

import shared.Command;

public class GetErrorHandler extends CommandProcessorHandler {
    private Command command;
    private String message;

    public GetErrorHandler(Command command){
        this.command = command;
    }

    @Override
    public boolean check(Command command) {
        if(command.getCommandId() == 99){
            this.message = (String)command.getContent();
            System.out.println(this.message);
        }
        return false;
    }
}
