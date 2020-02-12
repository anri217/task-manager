package shared;

public class Command {
    private int commandId;
    private Object content;

    public Command(int commandId, Object content){
        setCommandId(commandId);
        setContent(content);
    }

    public Command(){
        this.content = new Object();
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getCommandId() {
        return commandId;
    }
}
