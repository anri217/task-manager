package client;

import shared.JsonParser;

import java.io.DataInputStream;
import java.net.Socket;

public class NotificationListener extends Thread {
    private Socket socket;

    public NotificationListener(Socket socket) {
        this.socket = socket;
    }

    public void setExit(boolean exit) {
    }


    public void run() {
        try {
            CommandProcessor processor = CommandProcessor.getInstance();
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            while (!this.isInterrupted()) {
                Thread.sleep(2000);
                String answer = dis.readUTF();
                JsonParser parser = new JsonParser(answer);
                processor.processCommand(parser.parseCommand());
            }
            dis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
