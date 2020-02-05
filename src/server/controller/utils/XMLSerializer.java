package server.controller.utils;

import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;

public class XMLSerializer implements IOUtils {
    @Override
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException {

    }

    @Override
    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {

        return null;
    }
}
