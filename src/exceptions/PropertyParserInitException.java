package exceptions;

import java.io.FileNotFoundException;

public class PropertyParserInitException extends Exception {
    public PropertyParserInitException(String message) {
        super(message);
    }
}
