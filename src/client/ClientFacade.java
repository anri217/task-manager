package client;

import client.view.mainWindow.MainWindow;

import java.io.*;
import java.net.Socket;

public class ClientFacade {

    private String host;
    private int port;
    private Socket socket;

    public ClientFacade() {}

    public ClientFacade(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void connect(String[] args) {
        try (Socket socket = new Socket(host, port)) {

            System.out.println("Client connected to socket." + '\n');

            this.socket = socket;

            SendCommandHelper.getInstance().setFacade(this);

            MainWindow.run(args);
//            while (!socket.isClosed()) {
//                if (br.ready()) {
//                    System.out.println("Client start writing in channel...");
//                    String clientCommand = br.readLine();
//
//                    sendCommand(clientCommand, oos);
//                    System.out.println("Client sent message " + clientCommand + " to server.");
//
//                    if (clientCommand.equalsIgnoreCase("quit")) {
//                        System.out.println("Client kill connections");
//                        break;
//                    }
//
//                    System.out.println("Client sent message & start waiting for data from server...");
//                }
//            }
//            System.out.println("Closing connections & channels on clientSide - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(command);
        dos.flush();
    }
}
