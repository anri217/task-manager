package idgenerator;

/**
 * This is class generating id
 */

public class IdGenerator {

    private static IdGenerator instance;
    private static int id = 0;

    private IdGenerator() {};

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }


    public int getId() {
        return id++;
    }
}
