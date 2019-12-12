package model;

// todo is it model? why?
public class IdGenerator {
    // todo why static?
    private static int id = 0;

    public IdGenerator(int id) {
        IdGenerator.id = id;
    }

    static public int getId() {
        return id++;
    }
}
