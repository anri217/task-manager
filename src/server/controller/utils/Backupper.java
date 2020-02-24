package server.controller.utils;

import server.exceptions.BackupFileException;
import shared.exceptions.PropertyParserInitException;

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

    public void backupFunction(Object object, int current) throws PropertyParserInitException, BackupFileException {
        choose(current);
        ioUtils.serializeObject(object);
    }

    public Object restoreFunction(int current) throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        choose(current);
        return ioUtils.deserializeObject();
    }

    /**
     * For choose need serializer, if current = 1 BinarySerializer, else XMLSerializer
     *
     * @param current
     */
    private void choose(int current) {
        ioUtils = current == 1 ? BinarySerializer.getInstance() : XMLSerializer.getInstance();
    }
}
