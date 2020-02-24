package server;

import server.controller.utils.Paths;
import server.controller.utils.PropertyParser;
import server.controller.utils.portgenerator.PortGenerator;
import server.exceptions.PropertyParserInitException;
import shared.Command;
import shared.CommandCreator;
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

    private ServerSocket server;

    public ServerSocket getServer() {
        return server;
    }

    private Map<Integer, MonoClientThread> clients;

    public Map<Integer, MonoClientThread> getClients() {
        return clients;
    }

    public void setClients(Map<Integer, MonoClientThread> clients) {
        this.clients = clients;
    }

    private ExecutorService executeIt = Executors.newFixedThreadPool(2);

    private boolean exit;

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    private ServerFacade() {
        clients = new HashMap<>();
    }

    public void connect() {
        new Thread(() -> {
            PropertyParser parser = null;
            try {
                parser = new PropertyParser(Paths.SERVER);
            } catch (PropertyParserInitException e) {
                e.printStackTrace();
            }
            try (ServerSocket server = new ServerSocket(Integer.parseInt(parser.getProperty("port")))) {
                this.server = server;
                this.exit = true;
                while (!server.isClosed()) {
                    Socket clientSocket = server.accept();
                    int port = PortGenerator.getInstance().getPort();
                    MonoClientThread thread = new MonoClientThread(clientSocket, port);
                    clients.put(port, thread);
                    executeIt.execute(thread);
                    System.out.println("Connection accepted");
                }
                executeIt.shutdown();
            } catch (SocketException e) {
                if (!exit) {
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
