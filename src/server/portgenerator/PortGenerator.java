package server.portgenerator;

public class PortGenerator {

    private static PortGenerator instance;
    private static int port;

    private PortGenerator() {
        port = 8080;
    }

    public static synchronized PortGenerator getInstance() {
        if (instance == null) {
            instance = new PortGenerator();
        }
        return instance;
    }

    public int getPort() {
        return port++;
    }
}