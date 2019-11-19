package controller;

import java.io.*;
import model.*;

public class IOUtil {
    public static void serializeObject (JTasks jtasks, OutputStream out) throws IOException {
        if (jtasks != null) {
            // todo close stream or use try with resource for autocloseable objects
            // research: is the next object autocloseable?
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(jtasks);
        }
    }

    public static JTasks deserizeObject (InputStream in) throws IOException, ClassNotFoundException {
        JTasks result;
        ObjectInputStream ois = new ObjectInputStream(in);
        result = (JTasks) ois.readObject();
        return result;
    }
}
