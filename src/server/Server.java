package server;

import server.view.mainWindow.MainWindow;

public class Server {
    public static void main(String[] args) {
        MultiThreadServer multiThreadServer = new MultiThreadServer();
        multiThreadServer.start();
        MainWindow.run(args);
    }
}
