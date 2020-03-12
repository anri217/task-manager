package client.handlers.HandlerException;

import java.io.IOException;

public class HandleException extends Exception {

    public HandleException(Throwable e){
        initCause(e);
    }
}
