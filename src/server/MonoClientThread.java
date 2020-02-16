package server;

import server.portgenerator.PortGenerator;
import shared.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoClientThread implements Runnable {

    private static Socket clientDialog;
    private DataOutputStream outputStream;

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
            this.outputStream = stream;
            Writer writer = Writer.getInstance();
            writer.addStream(stream);
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

                if (answer.equalsIgnoreCase("{\"commandId\":5,\"content\":\" \"}")) {
                    System.out.println("Client initialize connections suicide ...");
                    Command command = CommandCreator.getInstance().createCommand(70, " ");
                    String jsonString = JsonBuilder.getInstance().createJsonString(command);
                    outputStream.writeUTF(jsonString);
                    outputStream.flush();
                    Writer.getInstance().close(outputStream);
                    break;
                }
            }
            stream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String entry) throws IOException {
        outputStream.writeUTF(entry);
        outputStream.flush();
    }
}
