package client;

import shared.commandTools.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFacade {

    private static ClientFacade instance;

    public static synchronized ClientFacade getInstance() {
        if (instance == null) {
            instance = new ClientFacade();
        }
        return instance;
    }

    private Socket socket;
    private int port;
    private NotificationListener listener;

    private ClientFacade() {
    }


    public int getPort() {
        return port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public NotificationListener getListener() {
        return listener;
    }

    void connect() throws IOException { // todo will be better if you caught IOExc and throw your own 
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        port = Integer.parseInt(dis.readUTF());
        this.socket.shutdownInput();
        ServerSocket server = new ServerSocket(port);
        Socket clientSocket = server.accept();
        server.close();
        listener = new NotificationListener(clientSocket);
        listener.start();
        CommandSender sender = CommandSender.getInstance();
        sender.setDos(dos);
        Command command = CommandCreator.getInstance().createCommand(ServerCommandIdConstants.GET_ALL_TASKS, " ", port);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
    }
}
