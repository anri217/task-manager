package shared;

public class Command {
    private int commandId;
    private Object content;
    //без сетов


    public Command( int commandId, Object content){
        this.commandId = commandId;
        this.content = content;
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
