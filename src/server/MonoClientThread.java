package server;

import server.portgenerator.PortGenerator;
import shared.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoClientThread implements Runnable {

    private static Socket clientDialog;

    public MonoClientThread(Socket client) {
        MonoClientThread.clientDialog = client;
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(clientDialog.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientDialog.getOutputStream())) {
            int port = PortGenerator.getInstance().getPort();
            dos.writeUTF(String.valueOf(port));
            dos.flush();
            Socket socket = new Socket("localhost", port);
            DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
            Writer writer = Writer.getInstance();
            writer.addStream(new DataOutputStream(socket.getOutputStream()));
            CommandProcessor processor = CommandProcessor.getInstance();
            while (!clientDialog.isClosed()) {
                Thread.sleep(2000);
                System.out.println("Server start waiting message from client");
                String answer = dis.readUTF();
                System.out.println(answer);
                JsonParser parser = new JsonParser(answer);
                parser.parseCommand();
                processor.processCommand(parser.getCommand());
                System.out.println("READ from clientDialog message - " + answer);

                if (answer.equalsIgnoreCase("quit")) {
                    System.out.println("Client initialize connections suicide ...");
                    dos.writeUTF("Server reply - " + answer + " - OK");
                    break;
                }
            }
            stream.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
