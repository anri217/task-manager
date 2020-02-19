package client;

import server.controller.utils.Paths;
import server.controller.utils.PropertyParser;
import server.exceptions.PropertyParserInitException;

import java.io.IOException;

public class Client2 {

    /**
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws PropertyParserInitException {
        try {
            PropertyParser propertyParser = new PropertyParser(Paths.CLIENT);
            ClientFacade clientFacade = new ClientFacade("192.168.31.193",
                    Integer.parseInt(propertyParser.getProperty("port")));
            clientFacade.connect(args);
        } catch (PropertyParserInitException | IOException ex) {
            throw new PropertyParserInitException(ex.getMessage());
        }
    }
}