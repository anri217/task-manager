package controller.util;

import exceptions.BackupFileException;
import exceptions.PropertyParserInitException;

import java.io.*;

/**
 * This is utility class used for serialization and deserialization objects
 */

public class IOUtil {

    private static IOUtil instance;
    private static final String PATH_TO_BACKUP_FILE = "path_to_backup_file", EX_STR = "Can't find backup file ";

    public static synchronized IOUtil getInstance() {
        if (instance == null) {
            instance = new IOUtil();
        }
        return instance;
    }

    private IOUtil() {};

    //for using methods without create objects
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException {
        if (obj != null) {
            PropertyParser propertyParser = new PropertyParser();
            String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
            try (OutputStream out = new FileOutputStream(new File(path));
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                // todo code format and close oos
                oos.writeObject(obj);
                oos.close();
            }
            catch (IOException ex) {
                throw new BackupFileException(EX_STR + ex.getMessage());
            }
        }
    }

    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        PropertyParser propertyParser = new PropertyParser();
        String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
        try (InputStream in = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Object obj = ois.readObject();
            ois.close();
            return obj;
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
