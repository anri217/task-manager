package exceptions;

public class BackupFileException extends Exception {
    public BackupFileException(String message) {
        super(message);
    }

    public BackupFileException() {
        super();
    }
}
