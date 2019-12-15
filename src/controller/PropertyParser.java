package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertyParser {
    private static final String PATH_TO_PROPERTIES = "staff/file.properties";
    private Properties properties;

    public PropertyParser() throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(fis);
        } catch (FileNotFoundException e) { // todo нормальная обработка эксепшенов, а не просто вываливать стек
            throw new IncorrectFileNameException("File not found or pathname is incorrect"); //я не знаю как назвать адекватно этот экспешн
        } catch (IOException e) {
            throw new IOException(); //и что с этим делать
        }
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
