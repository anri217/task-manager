package client;

public class SendCommandHelper {
    private static SendCommandHelper instance;
    private ClientFacade facade;

    private SendCommandHelper() {
        facade = new ClientFacade();
    }

    public static synchronized SendCommandHelper getInstance() {
        if (instance == null) {
            instance = new SendCommandHelper();
        }
        return instance;
    }

    public void setFacade(ClientFacade facade) {
        this.facade = facade;
    }

    public ClientFacade getFacade() {
        return facade;
    }
}
