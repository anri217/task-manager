package server.controller.utils;

import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;

public class Backupper {
    private IOUtils ioUtils;

    public Backupper() {
    }

    public Backupper(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }

    public void setIoUtils(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }

    public void backupFunction(Object object) throws PropertyParserInitException, BackupFileException {
        ioUtils.serializeObject(object);
    }

    public Object restoreFunction() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        return ioUtils.deserializeObject();
    }
}
