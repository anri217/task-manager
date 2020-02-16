package client;

import shared.Command;
import shared.JsonParser;

import java.io.DataInputStream;
import java.io.IOException;

public class Reader extends Thread {
    private DataInputStream dis;

    public Reader(DataInputStream dis) {
        this.dis = dis;
    }

    public void run() {
        try {
            CommandProcessor processor = CommandProcessor.getInstance();
            while (true) {
                Thread.sleep(2000);
                System.out.println("Client start waiting messages from server");
                String answer = dis.readUTF();
                JsonParser parser = new JsonParser(answer);
                parser.parseCommand();
                processor.processCommand(parser.getCommand());
                System.out.println("Client get message from server" + answer);
                if (answer.equals("{\"commandId\":70,\"content\":\" \"}")) {
                    System.out.println("Disconnect");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
