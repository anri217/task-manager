package shared;

public class CommandCreator {
    private static CommandCreator instance;

    private CommandCreator(){
    }

    public static synchronized CommandCreator getInstance(){
        if (instance == null){
            instance = new CommandCreator();
        }
        return instance;
    }

    public Command createCommand(int commandId, Object content){
        return new Command(commandId, content);
    }

}
