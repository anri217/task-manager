package server.controller.utils;

import com.thoughtworks.xstream.XStream;
import server.exceptions.BackupFileException;
import shared.exceptions.PropertyParserInitException;
import shared.utils.Paths;
import shared.utils.PropertyParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLSerializer implements IOUtils {

    private static XMLSerializer instance;
    private XStream xStream;

    private static final String PATH_TO_BACKUP_FILE = "path_to_backup_xml_file",
            EX_STR = "Can't find backup xml file ";


    public static synchronized XMLSerializer getInstance() {
        if (instance == null) {
            instance = new XMLSerializer();
        }
        return instance;
    }

    private XMLSerializer() {
        xStream = new XStream();
    }


    @Override
    public void serializeObject(Object obj) throws BackupFileException, PropertyParserInitException {
        if (obj != null) {
            PropertyParser propertyParser = new PropertyParser(Paths.FILE);
            String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
            try (FileOutputStream out = new FileOutputStream(new File(path))) {
                xStream.toXML(obj, out);
            } catch (IOException ex) {
                throw new BackupFileException(EX_STR + ex.getMessage());
            }
        }
    }

    @Override
    public Object deserializeObject() throws ClassNotFoundException, PropertyParserInitException, BackupFileException {
        PropertyParser propertyParser = new PropertyParser(Paths.FILE);
        String path = propertyParser.getProperty(PATH_TO_BACKUP_FILE);
        try (FileInputStream in = new FileInputStream(new File(path))) {
            return xStream.fromXML(in);
        } catch (IOException ex) {
            throw new BackupFileException("Can't find backup file " + ex.getMessage());
        }
    }
}
