package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer extends Thread {
    @Override
    public void run() {
        ServerFacade serverFacade = new ServerFacade();
        serverFacade.connect();
    }
}
