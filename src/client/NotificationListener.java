package client;

import client.commandTools.CommandProcessor;
import shared.commandTools.JsonParser;
import shared.constants.GeneralConstantsPack;

import java.io.DataInputStream;
import java.net.Socket;

// todo design troubles, for this class will be enough have only InputStream. For what socket?
public class NotificationListener extends Thread {
    private Socket socket;

    public NotificationListener(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            CommandProcessor processor = CommandProcessor.getInstance();
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            while (!this.isInterrupted()) {
                Thread.sleep(GeneralConstantsPack.SLEEP_TIME);
                String answer = dis.readUTF();
                processor.processCommand(JsonParser.getInstance().parseCommand(answer));
            }
            dis.close();
        } catch (Exception ignored) {
        }
    }
}
