package controller;

import java.io.*;

public class IOUtil {
    
    // todo static and non static methods? for what?
    public static void serializeObject(Object obj) throws IOException {
        if (obj != null) {
            PropertyParser propertyParser = new PropertyParser();
            String path = propertyParser.getProperty("path_backup_file");
            try (OutputStream out = new FileOutputStream(new File(path)); //pathname is abstract...
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(obj);
            }
        }
    }

    public static Object deserializeObject() throws IOException, ClassNotFoundException {
        PropertyParser propertyParser = new PropertyParser();
        String path = propertyParser.getProperty("path_backup_file");
        try (InputStream in = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(in)) {
            return ois.readObject();
        }
    }

    public static void backupFunction(Object object) throws IOException {
        serializeObject(object);
    }

    public static void restoreFunction() throws IOException, ClassNotFoundException {
        deserializeObject();
    }
}
