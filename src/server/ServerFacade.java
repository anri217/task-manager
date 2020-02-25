package server;

import server.controller.utils.PortGenerator;
import shared.Command;
import shared.CommandCreator;
import shared.NamedConstants;
import shared.exceptions.PropertyParserInitException;
import shared.utils.Paths;
import shared.utils.PropertyParser;
import shared.view.AlertShowing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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

    private ServerSocket serverSocket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private Map<Integer, MonoClientThread> clientThreadMap;

    public Map<Integer, MonoClientThread> getClientThreadMap() {
        return clientThreadMap;
    }


    private ExecutorService executeIt = Executors.newFixedThreadPool(2);

    private boolean exit;

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    private ServerFacade() {
        clientThreadMap = new HashMap<>();
    }

    public boolean isEmptyMap() {
        return this.clientThreadMap.isEmpty();
    }

    public MonoClientThread getThread(int port) {
        return this.clientThreadMap.get(port);
    }

    public void sendAll(String entry) throws IOException {
        for(int port : this.clientThreadMap.keySet()) {
            this.clientThreadMap.get(port).sendCommand(entry);
        }
    }

    public void removeThread(int port) {
        this.clientThreadMap.remove(port);
    }

    public void connect() {
        new Thread(() -> {
            PropertyParser parser = null;
            try {
                parser = new PropertyParser(Paths.SERVER);
            } catch (PropertyParserInitException e) {
                e.printStackTrace();
            }
            try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(parser.getProperty(NamedConstants.PROPERTY_NAME_PORT)))) {
                this.serverSocket = serverSocket;
                this.exit = false;
                while (!serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    int port = PortGenerator.getInstance().getPort();
                    MonoClientThread thread = new MonoClientThread(clientSocket, port);
                    clientThreadMap.put(port, thread);
                    executeIt.execute(thread);
                }
                executeIt.shutdown();
            } catch (SocketException e) {
                if (exit) {
                    Command command = CommandCreator.getInstance().createCommand(6, " ");
                    try {
                        CommandProcessor.getInstance().processCommand(command);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                AlertShowing.showAlert(e.getMessage());
            }
        }).start();
    }
}
