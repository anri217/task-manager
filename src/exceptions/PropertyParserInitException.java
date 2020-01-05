package exceptions;

import java.io.FileNotFoundException;

/**
 * This is exception for opening properties files
 */

public class PropertyParserInitException extends Exception {
    public PropertyParserInitException(String message) {
        super(message);
    }

    public PropertyParserInitException() {
        super();
    }
}
