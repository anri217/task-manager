package server;

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

               if (ins.ready()) {
                   System.out.println("Main Server found any messages in channel, let's look at them.");
                   String serverCommand = ins.readLine();
                   if (serverCommand.equalsIgnoreCase("quit")) {
                       System.out.println("Main Server initiate exiting...");
                       server.close();
                       break;
                   }
               }

               Socket client = server.accept();

               executeIt.execute(new MonoThreadClientHandler(client));
               System.out.print("Connection accepted.");
           }
           executeIt.shutdown();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
