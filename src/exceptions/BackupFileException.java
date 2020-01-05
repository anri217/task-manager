package exceptions;

/**
 * This is exception for opening backup files
 */

public class BackupFileException extends Exception {
    public BackupFileException(String message) {
        super(message);
    }

    public BackupFileException() {
        super();
    }
}
