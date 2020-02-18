package server;

import java.io.IOException;

public class MultiThreadServer extends Thread {
    @Override
    public void run() {
        try {
            ServerFacade.getInstance().connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
