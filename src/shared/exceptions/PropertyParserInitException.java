package shared.exceptions;

/**
 * Exception for opening properties files
 */

public class PropertyParserInitException extends Exception {

    public PropertyParserInitException(String message) {
        super(message);
    }

    public PropertyParserInitException() {
        super();
    }
}
