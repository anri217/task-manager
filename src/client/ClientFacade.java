package client;

import client.view.mainWindow.MainWindow;
import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;

import java.io.*;
import java.net.ServerSocket;
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
            String port = dis.readUTF();
            System.out.println("Get port from client: " + port);
            ServerSocket server = new ServerSocket(Integer.parseInt(port));
            Socket client = server.accept();
            System.out.println("Connection accepted");
            Reader reader = new Reader(new DataInputStream(client.getInputStream()));
            reader.start();
            CommandSender sender = CommandSender.getInstance();
            sender.setDos(dos);
            Command command = CommandCreator.getInstance().createCommand(0, " ");
            String jsonString = JsonBuilder.getInstance().createJsonString(command);
            CommandSender.getInstance().sendCommand(jsonString);
            MainWindow.run(args);
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
