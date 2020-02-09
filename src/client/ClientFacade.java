package client;

import shared.CommandSender;

import java.io.*;
import java.net.Socket;

public class ClientFacade {
    private String host;
    private int port;

    public ClientFacade() {
    }

    public ClientFacade(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void connect() throws IOException {
        try (Socket socket = new Socket(host, port);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            CommandSender commandSender = CommandSender.getInstance();
            commandSender.setDos(dos);
            while (!socket.isClosed()) {
                System.out.println("Client start writing in channel...");
                String clientCommand = br.readLine();
                commandSender.sendCommand(clientCommand);


                System.out.println("Client sent message " + clientCommand + " to server.");
                if (clientCommand.equalsIgnoreCase("quit")) {
                    System.out.println("Client kill connections");
                    break;
                }

            }
            System.out.println("Closing connections & channels on clientSide - DONE.");
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    public void closeConnection() {

    }


    /*public void waitCommand() throws IOException {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            if (br.ready()) {
                String clientCommand = br.readLine();

                System.out.println("Server sent message " + clientCommand + " to server.");

                break;
            }
        }
    }*/
}
