package server.controller.utils;

import server.exceptions.BackupFileException;
import shared.exceptions.PropertyParserInitException;
import shared.utils.Paths;
import shared.utils.PropertyParser;

import java.io.*;

/**
 * Utility class used for serialization and deserialization objects
 */

public class BinarySerializer implements IOUtils {

    private static BinarySerializer instance;

    private static final String PROPERTY_NAME_OF_PATH = "path_to_backup_file",
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
            String path = propertyParser.getProperty(PROPERTY_NAME_OF_PATH);
            File file = new File(path);
            try {
                file.createNewFile();
            } catch(IOException ignored) {
            }
            try (OutputStream out = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(obj);
            } catch (IOException e) {
                throw new BackupFileException(EX_STR + e.getMessage());
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
        String path = propertyParser.getProperty(PROPERTY_NAME_OF_PATH);
        File file = new File(path);
        try {
            file.createNewFile();
        } catch(IOException ignored) {
        }
        try (InputStream in = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (IOException e) {
            throw new BackupFileException(EX_STR + e.getMessage());
        }
    }
}
