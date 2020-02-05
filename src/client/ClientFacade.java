package client;

import java.io.*;
import java.net.Socket;

public class ClientFacade {

    private String host;
    private int port;

    public ClientFacade(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void connect(String[] args) {
        try (Socket socket = new Socket(host, port);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {

            System.out.println("Client connected to socket." + '\n');

            while (!socket.isClosed()) {
                if (br.ready()) {
                    System.out.println("Client start writing in channel...");
                    String clientCommand = br.readLine();

                    sendCommand(clientCommand, oos);
                    System.out.println("Client sent message " + clientCommand + " to server.");

                    if (clientCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Client kill connections");
                        break;
                    }

                    System.out.println("Client sent message & start waiting for data from server...");
                }
            }
            System.out.println("Closing connections & channels on clientSide - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendCommand(String command, DataOutputStream dos) throws IOException {
        dos.writeUTF(command);
        dos.flush();
    }
}
