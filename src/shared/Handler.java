package shared;

public interface Handler {

    public void handle(Command command) throws Exception;
}
