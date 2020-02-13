package client;

import shared.Command;

public abstract class CommandProcessorHandler {
    private CommandProcessorHandler next;

    public CommandProcessorHandler linkWith(CommandProcessorHandler next){
        this.next = next;
        return next;
    }

    public abstract boolean check(Command command);

    protected boolean checkNext(Command command){
        if (next == null){
            return true;
        }
        return next.check(command);
    }

}
