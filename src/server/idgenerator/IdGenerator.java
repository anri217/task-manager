package server.idgenerator;

/**
 * Class generating id for comfortable iteration
 */

public class IdGenerator {

    private static IdGenerator instance;
    private static int id = 0;

    private IdGenerator() {
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Getter function
     *
     * @return id
     */

    public int getId() {
        return id++;
    }
}
