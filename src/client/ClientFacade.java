package client;


public class ClientFacade {
    void sendCommand(String command) {
        //build json and send to server json file
        //may be we can separate this method like addTask, changeTask and etc
    }

    void refresh(){
        //refreshing table
    }

    void showNotification(){
        //show notification, in args should be task
    }

    void sendAll(){
        //sending all table items to server
    }
}
