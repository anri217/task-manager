package server;

import server.portgenerator.PortGenerator;
import shared.CommandSender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerFacade {

   private Map<Integer, Socket> clients;

   private static ExecutorService executeIt = Executors.newFixedThreadPool(2);

   public ServerFacade() {
       clients = new HashMap<Integer, Socket>();
   }

   public void connect() {
       try (ServerSocket server = new ServerSocket(3345)) {
           BufferedReader ins = new BufferedReader(new InputStreamReader(System.in));
           while (!server.isClosed()) {
               Socket client = server.accept();

               executeIt.execute(new MonoThreadClientHandler(client));
               System.out.println("Connection accepted");
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
