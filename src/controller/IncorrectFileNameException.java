package controller;

import java.io.FileNotFoundException;

public class IncorrectFileNameException extends FileNotFoundException {
    public IncorrectFileNameException(String message) {
        super(message);
    }
}
