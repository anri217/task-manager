package server;

import server.portgenerator.PortGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerFacade {
    private static ServerFacade instance;

    public static synchronized ServerFacade getInstance() {
        if (instance == null) {
            instance = new ServerFacade();
        }
        return instance;
    }

    private Map<Integer, MonoClientThread> clients; //todo port and monoclientthread

    public Map<Integer, MonoClientThread> getClients() {
        return clients;
    }

    public void setClients(Map<Integer, MonoClientThread> clients) {
        this.clients = clients;
    }

    private static ExecutorService executeIt = Executors.newFixedThreadPool(5);


    private ServerFacade() {
        clients = new HashMap<Integer, MonoClientThread>();
    }

    public void connect() {
        try (ServerSocket server = new ServerSocket(3345)) {
            BufferedReader ins = new BufferedReader(new InputStreamReader(System.in));
            while (!server.isClosed()) {
                Socket client = server.accept();
                int port = PortGenerator.getInstance().getPort();
                MonoClientThread thread = new MonoClientThread(client,port);
                clients.put(port, thread);
                executeIt.execute(thread); //change name of class
                System.out.println("Connection accepted");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
