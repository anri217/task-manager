package client;

import client.view.mainWindow.MainWindow;

import java.io.*;
import java.net.Socket;

public class ClientFacade {

    private String host;
    private int port;
    private DataOutputStream dos;
    private DataInputStream dis;

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public ClientFacade() {
    }

    public ClientFacade(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void connect() throws IOException {
        Socket socket = new Socket(host, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());


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
}

    public void sendCommand(String command) throws IOException {
        dos.writeUTF(command);
        dos.flush();
    }

    public void waitCommand() throws IOException {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            if (br.ready()) {
                String clientCommand = br.readLine();

                System.out.println("Server sent message " + clientCommand + " to server.");

                break;
            }
        }
    }
}
