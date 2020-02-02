package server;

//singleton class

import java.net.Socket;

public class ServerFacade {
    //map
   private int port;

   public ServerFacade(int port) {
       this.port = port;
   }

    void connect() {

    }



    void sendCommand(String command) {
        //build json and send to client json file
        //may be we can separate this method like addTask, changeTask and etc
    }
}
