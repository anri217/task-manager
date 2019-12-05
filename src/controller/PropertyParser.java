package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertyParser {
    private static final String PATH_TO_PROPERTIES = "src/file.properties";
    private Properties properties;

    public PropertyParser() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties() throws IOException {
        return "icons: " + properties.getProperty("icons") + "\npath to backup file: "
                + properties.getProperty("path_to_backup_file") + "\npath to sounds: " + properties.getProperty("path_to_sounds");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }


}
