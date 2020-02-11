package shared;

import server.Command;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandSender {

    private static CommandSender instance;

    public static synchronized CommandSender getInstance() {
        if (instance == null) {
            instance = new CommandSender();
        }
        return instance;
    }

    private CommandSender() {}

    private DataOutputStream dos;

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public void sendCommand(String command) throws IOException {
        dos.writeUTF(command);
        dos.flush();
    }
}
