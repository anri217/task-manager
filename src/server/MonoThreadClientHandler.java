package server;

import server.exceptions.PropertyParserInitException;
import shared.CommandSender;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(clientDialog.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientDialog.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!clientDialog.isClosed()) {
                String entry = dis.readUTF();
                System.out.println("READ from clientDialog message - " + entry);

                if (entry.equalsIgnoreCase("quit")) {
                    System.out.println("Client initialize connections suicide ...");
                    dos.writeUTF("Server reply - " + entry + " - OK");
                    break;
                }
                System.out.println("Server start waiting new message from client");
            }
            clientDialog.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
