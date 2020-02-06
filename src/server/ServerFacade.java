package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerFacade {
    //map
   private int port;

   private static ExecutorService executeIt = Executors.newCachedThreadPool();

   public ServerFacade(int port) {
       this.port = port;
   }

   public ServerFacade() {}

   public void connect() {
       try (ServerSocket server = new ServerSocket(3345)) {
           while (!server.isClosed()) {
               Socket client = server.accept();

               BufferedReader ins = new BufferedReader(new InputStreamReader(client.getInputStream()));

               if (ins.ready()) {
                   System.out.println("Main Server found any messages in channel, let's look at them.");
                   String serverCommand = ins.readLine();
                   if (serverCommand.equalsIgnoreCase("quit")) {
                       System.out.println("Main Server initiate exiting...");
                       server.close();
                       break;
                   }
               }

               executeIt.execute(new MonoThreadClientHandler(client));
               System.out.print("Connection accepted.");
           }
           executeIt.shutdown();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void sendCommand(String command) {
       //
    }
}
