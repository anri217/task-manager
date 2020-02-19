package server;

import shared.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoClientThread implements Runnable {

    private static Socket clientDialog;
    private int port;
    private boolean exit;
    private DataOutputStream stream;


    public MonoClientThread(Socket client, int port) {
        MonoClientThread.clientDialog = client;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    @Override
    public void run() {
        this.exit = true;
        try (DataInputStream dis = new DataInputStream(clientDialog.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientDialog.getOutputStream())) {
            dos.writeUTF(String.valueOf(this.port));
            dos.flush();
            String host = dis.readUTF();
            System.out.println(host);
            Socket socket = new Socket(host, this.port);
            this.stream = new DataOutputStream(socket.getOutputStream());
            CommandProcessor processor = CommandProcessor.getInstance();
            while (this.exit) {
                Thread.sleep(2000);
                System.out.println("Server start waiting message from client");
                String answer = dis.readUTF();
                System.out.println(answer);
                JsonParser parser = new JsonParser(answer);
                parser.parseCommand();
                processor.processCommand(parser.getCommand());
                System.out.println("READ from clientDialog message - " + answer);
            }
            System.out.println("Client disconnected");
            this.stream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String entry) throws IOException {
        this.stream.writeUTF(entry);
        this.stream.flush();
    }
}
