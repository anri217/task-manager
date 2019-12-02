package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyParser {
    private static final String PATH_TO_PROPERTIES = "src/file.properties";

    public String getProperties() throws IOException {
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(fis);

            String icons = properties.getProperty("icons");
            String pathToBackupFile = properties.getProperty("pathToBackupFile");
            String pathToSounds = properties.getProperty("pathToSounds");

            return "icons: " + icons + "\npath to backup file: " + pathToBackupFile + "\npath to sounds: " + pathToSounds;
        }
    }
}
