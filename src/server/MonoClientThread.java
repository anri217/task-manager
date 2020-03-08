package server;

import server.commandTools.CommandProcessor;
import shared.constants.GeneralConstantsPack;
import shared.commandTools.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoClientThread extends Thread {

    private Socket clientSocket;
    private int port;
    private boolean exit;
    private DataOutputStream stream;


    public MonoClientThread(Socket client, int port) {
        this.clientSocket = client;
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
        try (DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
             DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream())) {
            dos.writeUTF(String.valueOf(this.port));
            dos.flush();
            try (Socket secondClientSocket = new Socket(this.clientSocket.getInetAddress().getHostAddress(), this.port);
                 DataOutputStream secondDos = new DataOutputStream(secondClientSocket.getOutputStream())) {
                this.stream = secondDos;
                CommandProcessor processor = CommandProcessor.getInstance();
                while (this.exit) {
                    Thread.sleep(GeneralConstantsPack.SLEEP_TIME);
                    String answer = dis.readUTF();
                    System.out.println(answer);
                    try {
                        processor.processCommand(JsonParser.getInstance().parseCommand(answer));
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (InterruptedException ignored) {

        } catch (IOException e) {
            if (!this.exit) {
                System.exit(0);
            }
        } finally {
            try {
                this.clientSocket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void sendCommand(String entry) throws IOException {
        this.stream.writeUTF(entry);
        this.stream.flush();
    }
}
