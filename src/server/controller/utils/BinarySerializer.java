package server.controller.utils;

import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;

import java.io.*;

/**
 * Utility class used for serialization and deserialization objects
 */

public class BinarySerializer implements IOUtils {

    private static BinarySerializer instance;

    private static final String PATH_TO_BACKUP_FILE = "path_to_backup_file",
            EX_STR = "Can't find backup file ";

    /**
     * Singleton implementation
     *
     * @return current single object
     */

    public static synchronized BinarySerializer getInstance() {
        if (instance == null) {
            instance = new BinarySerializer();
        }
        return instance;
    }

    private BinarySerializer() {
    }

    /**
     * Serialization function
     *
     * @param obj - serializable object
     * @throws BackupFileException
     * @throws PropertyParserInitException
     */

    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException {
        if (obj != null) {
            PropertyParser propertyParser = new PropertyParser(Paths.FILE);
            String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
            try (OutputStream out = new FileOutputStream(new File(path));
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(obj);
            } catch (IOException ex) {
                throw new BackupFileException(EX_STR + ex.getMessage());
            }
        }
    }

    /**
     * Deserialization function
     *
     * @return deserialized object
     * @throws ClassNotFoundException
     * @throws PropertyParserInitException
     * @throws BackupFileException
     */

    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        PropertyParser propertyParser = new PropertyParser(Paths.FILE);
        String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
        try (InputStream in = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (IOException ex) {
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
