package shared;

public class CommandCreator {

    public Command createCommand(int commandId, Object content){
        return new Command(commandId, content);
    }

}
