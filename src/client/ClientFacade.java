package client;

import client.view.mainWindow.MainWindow;
import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ClientFacade {

    private String host;
    private int port;
    static private int secPort;
    static private HashMap<Integer, Reader> readers;

    public ClientFacade(String host, int port) {
        readers = new HashMap<Integer, Reader>();
        this.host = host;
        this.port = port;
    }

    static public HashMap<Integer, Reader> getReaders() {
        return readers;
    }

    static public int getSecPort() {
        return secPort;
    }

    void connect(String[] args) throws IOException {
        try (Socket socket = new Socket(host, port);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            String port = dis.readUTF();
            secPort = Integer.parseInt(port);
            System.out.println("Get port from client: " + port);
            try (ServerSocket server = new ServerSocket(Integer.parseInt(port));
                 Socket client = server.accept()) {
                System.out.println("Connection accepted");
                Reader reader = new Reader(new DataInputStream(client.getInputStream()));
                readers.put(Integer.parseInt(port), reader);
                reader.start();
                CommandSender sender = CommandSender.getInstance();
                sender.setDos(dos);
                Command command = CommandCreator.getInstance().createCommand(0, " ", secPort);
                String jsonString = JsonBuilder.getInstance().createJsonString(command);
                CommandSender.getInstance().sendCommand(jsonString);
                MainWindow.run(args);
            }
        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }
}
