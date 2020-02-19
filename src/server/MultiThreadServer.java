package server;

import server.exceptions.PropertyParserInitException;

import java.io.IOException;

public class MultiThreadServer extends Thread {
    @Override
    public void run() {
        try {
            ServerFacade.getInstance().connect();
        } catch (IOException | PropertyParserInitException e) {
            e.printStackTrace();
        }
    }
}
