
// todo nigo is it work? looks like you do not try to generate value
// also please use Code Convention
// see requirements in issue for this class
public class IdGenerator {
    private static int id = 0;

    public IdGenerator(int id) {
        this.id = id;
    }

    static public int getId() {
        return id++;
    }
}
