package server.exceptions;

/**
 * Exception for opening backup files
 */

public class BackupFileException extends Exception {

    public BackupFileException(String message) {
        super(message);
    }

    public BackupFileException() {
        super();
    }
}
