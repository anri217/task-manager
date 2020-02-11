package client;

import server.Command;
import server.controller.Notifier;
import shared.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommandProcessor {
    private Command command;

    public CommandProcessor(Command command){
        this.command = command;
    }

    public void chooseActivity(){
        switch (command.getCommand_id()){
            case(0): getAll();
            break;
            case(1): getNotification();
            break;
            case(99): getError();
            break;
        }
    }

    private void getError(){
        String errorMessage = (String) command.getContent();
        System.out.println(errorMessage);
    }

    private void getNotification(){
        Task task = (Task)command.getContent();
        Notifier notifier = new Notifier();
        notifier.createNotification(task);
    }

    private void getAll(){
        List<Task> tasks = (List<Task>) command.getContent();
        System.out.println(tasks); // заменить потом на вызов рефреша в табличке
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
