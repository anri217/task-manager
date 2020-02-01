package client;

import javafx.beans.property.Property;
import server.controller.utils.Paths;
import server.controller.utils.PropertyParser;
import server.exceptions.PropertyParserInitException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws PropertyParserInitException {

// запускаем подключение сокета по известным координатам и нициализируем приём сообщений с консоли клиента
        try {
            PropertyParser propertyParser = new PropertyParser(Paths.CLIENT);

            ClientFacade clientFacade = new ClientFacade(propertyParser.getProperty("host"),
                                                            Integer.parseInt(propertyParser.getProperty("port")));
            clientFacade.connect(args);
        } catch (PropertyParserInitException ex) {
            throw new PropertyParserInitException(ex.getMessage());
        }
    }
}