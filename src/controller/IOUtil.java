package controller;

import java.io.*;

public class IOUtil {
    public static void serializeObject(Object obj) throws IOException {
        if (obj != null) {
            // todo close stream or use try with resource for autocloseable objects
            // research: is the next object autocloseable?
            try (OutputStream out = new FileOutputStream(new File("Z://directory")); //pathname is abstract...
                 ObjectOutputStream oos = new ObjectOutputStream(out)) {
                oos.writeObject(obj);
            }
        }
    }

    public static Object deserializeObject() throws IOException, ClassNotFoundException {
        try (InputStream in = new FileInputStream(new File("Z://directory"));
             ObjectInputStream ois = new ObjectInputStream(in)) {
            return ois.readObject();
        }
    }
}
