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
    private Map<Integer, MonoClientThread> clients; //todo port and monoclientthread

    private static ExecutorService executeIt = Executors.newFixedThreadPool(5);

    public ServerFacade() {
        clients = new HashMap<Integer, MonoClientThread>();
    }

    public void connect() {
        try (ServerSocket server = new ServerSocket(3345)) {
            BufferedReader ins = new BufferedReader(new InputStreamReader(System.in));
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoClientThread(client)); //change name of class
                System.out.println("Connection accepted");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
