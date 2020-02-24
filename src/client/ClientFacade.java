package client;

import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFacade {

    private Socket socket;
    static private int port;
    static private NotificationListener listener;
    static private DataInputStream dis;

    public ClientFacade(Socket socket) {
        this.socket = socket;
    }

    public static DataInputStream getDis() {
        return dis;
    }

    static public int getPort() {
        return port;
    }

    public static NotificationListener getListener() {
        return listener;
    }

    void connect() throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        port = Integer.parseInt(dis.readUTF());
        System.out.println("Get port from client: " + port);
        ServerSocket server = new ServerSocket(port);
        Socket clientSocket = server.accept();
        System.out.println("Connection accepted");
        server.close();
        listener = new NotificationListener(clientSocket);
        listener.start();
        CommandSender sender = CommandSender.getInstance();
        sender.setDos(dos);
        Command command = CommandCreator.getInstance().createCommand(0, " ", port);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
    }
}
