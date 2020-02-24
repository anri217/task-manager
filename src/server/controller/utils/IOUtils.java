package server.controller.utils;

import server.exceptions.BackupFileException;
import shared.exceptions.PropertyParserInitException;

public interface IOUtils {
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException;

    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException;
}
