package client.handlers.HandlerException;

import java.io.IOException;

public class HandleException extends IOException {

    public HandleException(Throwable e){
        initCause(e);
    }
}
