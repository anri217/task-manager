package controller;

import exceptions.PropertyParserInitException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyParser {
    private static final String PATH_TO_PROPERTIES = "staff/file.properties";
    private Properties properties;

    public PropertyParser() throws IOException, PropertyParserInitException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(fis);
        } // todo нормальная обработка эксепшенов, а не просто вываливать стек
        catch (IOException ex) {
            throw new PropertyParserInitException("Can't init file" + ex.getMessage());
        }
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
