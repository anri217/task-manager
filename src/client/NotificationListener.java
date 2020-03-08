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
                Thread.sleep(2000);//todo в константы время
                String answer = dis.readUTF();
                processor.processCommand(JsonParser.getInstance().parseCommand(answer));
            }
            dis.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
