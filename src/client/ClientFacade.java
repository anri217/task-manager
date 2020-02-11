package client;

import client.view.mainWindow.MainWindow;
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

    void connect(String[] args) throws IOException {
        try (Socket socket = new Socket(host, port);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            CommandSender sender = CommandSender.getInstance();
            sender.setDos(dos);
            ReadMsg readMsg = new ReadMsg(dis);
            readMsg.start();
            MainWindow.run(args);
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
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
