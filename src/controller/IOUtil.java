package controller;

import java.io.*;
import model.*;

public class IOUtil {
    public static void serializeObject (Journal journal, OutputStream out) throws IOException {
        if (journal != null) {
            // todo close stream or use try with resource for autocloseable objects
            // research: is the next object autocloseable?
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(journal);
        }
    }

    public static Journal deserializeObject (InputStream in) throws IOException, ClassNotFoundException {
        Journal result;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (Journal) ois.readObject();
        ois.close();
        return result;
    }
}
