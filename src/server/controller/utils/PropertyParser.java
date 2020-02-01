package server.controller.utils;

import server.exceptions.PropertyParserInitException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class used to work with properties
 */

public class PropertyParser {
    private static final String EX_STR = "Can't init file ", CLIENT_PATH = "staff\\properties\\client.properties",
    FILE_PATH = "staff\\properties\\file.properties", SERVER_PATH = "staff\\properties\\server.properties";
    private Properties properties;




    public static String getExStr() {
        return EX_STR;
    }

    /**
     * Constructor loading data in properties
     *
     * @throws PropertyParserInitException
     */

    public PropertyParser(Paths paths) throws PropertyParserInitException {
        String pathToProperties = "";
        switch(paths.ordinal()){
            case(0):
                pathToProperties = CLIENT_PATH;
                break;
            case(1):
                pathToProperties = SERVER_PATH;
                break;
            case(2):
                pathToProperties = FILE_PATH;
                break;
        }
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(pathToProperties)) {
            properties.load(fis);
        } catch (IOException ex) {
            throw new PropertyParserInitException(EX_STR + ex.getMessage());
        }
    }


    /**
     * Function returning the desired property
     *
     * @param key - desired key
     * @return desired property
     */

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
