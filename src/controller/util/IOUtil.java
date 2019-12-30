package controller.util;

import exceptions.BackupFileException;
import exceptions.PropertyParserInitException;

import java.io.*;

public class IOUtil {

    private static IOUtil instance;

    public static synchronized IOUtil getInstance() {
        if (instance == null) {
            instance = new IOUtil();
        }
        return instance;
    }

    private IOUtil() {};

    // todo static and non static methods? for what?
    //for using methods without create objects
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException {
        if (obj != null) {
            PropertyParser propertyParser = new PropertyParser();
            String path = propertyParser.getProperty("path_to_backup_file");
            try (OutputStream out = new FileOutputStream(new File(path));
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(obj);
            }
            catch (IOException ex) {
                throw new BackupFileException("Can't find backup file " + ex.getMessage());
            }
        }
    }

    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        PropertyParser propertyParser = new PropertyParser();
        String path = propertyParser.getProperty("path_to_backup_file");
        try (InputStream in = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(in)) {
            return ois.readObject();
        }
        catch (IOException ex) {
            throw new BackupFileException("Can't find backup file " + ex.getMessage());
        }
    }

    public void backupFunction(Object object) throws PropertyParserInitException, BackupFileException {
        serializeObject(object);
    }

    public Object restoreFunction() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
       return deserializeObject();
    }
}
