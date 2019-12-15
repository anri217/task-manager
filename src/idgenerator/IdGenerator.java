package idgenerator;

// todo is it model? why?
public class IdGenerator {
    // todo why static?
    //getId static method

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
