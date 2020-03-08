package shared;

public class Command {
    private int commandId;
    private Object content;
    private int port;


    public Command(int commandId, Object content) {
        this.commandId = commandId;
        this.content = content;
    }


    public int getPort() {
        return port;
    }

    public Command(int commandId, Object content, int port) {
        this.commandId = commandId;
        this.content = content;
        this.port = port;
    }

    public Command() {
        this.content = new Object();
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    } //todo неиспользуемое поле???

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getCommandId() {
        return commandId;
    }

}
