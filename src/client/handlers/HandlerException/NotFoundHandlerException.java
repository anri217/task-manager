package client.handlers.HandlerException;

public class NotFoundHandlerException extends Exception {
    private int commandId;

    public NotFoundHandlerException(String message) {
        super(message);
    }

    public NotFoundHandlerException(){
        super();
    }
}
