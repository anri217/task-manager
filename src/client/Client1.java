package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

// запускаем подключение сокета по известным координатам и инициализируем приём сообщений с консоли клиента



        try (Socket socket = new Socket("localhost", 3345);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream()))
        {

            System.out.println("Client connected to socket.");
            System.out.println();

// проверяем живой ли канал и работаем если живой
            while(!socket.isOutputShutdown()){

// ждём консоли клиента на предмет появления в ней данных
                if(br.ready()){

// данные появились - работаем
                    System.out.println("Client start writing in channel...");
                    String clientCommand = br.readLine();

// пишем данные с консоли в канал сокета для сервера
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Client sent message " + clientCommand + " to server.");
// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// проверяем условие выхода из соединения
                    if(clientCommand.equalsIgnoreCase("quit")){

// если условие выхода достигнуто разъединяемся
                        System.out.println("Client kill connections");

                        break;
                    }

// если условие разъединения не достигнуто продолжаем работу
                    System.out.println("Client sent message & start waiting for data from server...");

                }
            }
// на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}