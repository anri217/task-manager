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
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            CommandSender sender = CommandSender.getInstance();
            sender.setDos(dos);
            ReadMsg readMsg = new ReadMsg(dis);
            readMsg.start();
            MainWindow.run(args);
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
