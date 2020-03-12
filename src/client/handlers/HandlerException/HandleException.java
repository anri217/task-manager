package client.handlers.HandlerException;

import java.io.IOException;

public class HandleException extends IOException { // todo why does it extend IOException?

    public HandleException(Throwable e){
        initCause(e);
    }
}
