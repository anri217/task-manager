package shared;

public class PortGenerator {

    private static PortGenerator instance;
    private static int port;

    private PortGenerator() {
        port = 1025;
    }

    public static synchronized PortGenerator getInstance() {
        if (instance == null) {
            instance = new PortGenerator();
        }
        return instance;
    }

    /**
     * Getter function
     *
     * @return id
     */

    public int getPort() {
        return port++;
    }
}