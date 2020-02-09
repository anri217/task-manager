package server;

public class Server {
    public static void main(String[] args) {
        MultiThreadServer multiThreadServer = new MultiThreadServer();
        multiThreadServer.run();
        //MainWindow.run(args);
    }
}
