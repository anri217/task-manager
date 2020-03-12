package client.handlers.HandlerException;

public class NotFoundHandlerException extends Exception { // todo who is my parent?
    // todo wtf?
    private int commandId;

    public NotFoundHandlerException(String message) {
        super(message);
    }

    public NotFoundHandlerException(){
        super();
    }
}
