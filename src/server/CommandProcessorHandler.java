package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import shared.Command;

public abstract class CommandProcessorHandler {
    private CommandProcessorHandler next;

    public CommandProcessorHandler linkWith(CommandProcessorHandler next){
        this.next = next;
        return next;
    }

    public abstract boolean check(Command command) throws JsonProcessingException;

    protected boolean checkNext(Command command) throws JsonProcessingException {
        if (next == null){
            return true;
        }
        return next.check(command);
    }
}
