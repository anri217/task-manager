package controller;

import java.io.*;
import model.*;

public class IOUtil {
    public static void serializeObject (Journal jtasks, OutputStream out) throws IOException {
        if (jtasks != null) {
            // todo close stream or use try with resource for autocloseable objects
            // research: is the next object autocloseable?
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(jtasks);
        }
    }

    public static Journal deserizeObject (InputStream in) throws IOException, ClassNotFoundException {
        Journal result;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (Journal) ois.readObject();
        return result;
    }
}
