package client;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadMsg extends Thread {
    private DataInputStream dis;

    public ReadMsg(DataInputStream dis) {
        this.dis = dis;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Client start waiting messages from server");
                String answer = dis.readUTF();
                System.out.println("Client get message from server" + answer);
                if (answer.equals("quit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
