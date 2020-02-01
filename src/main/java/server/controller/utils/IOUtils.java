package server.controller.utils;

import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;

public interface IOUtils {
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException;

    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException;
}
