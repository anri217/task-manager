package client;

import shared.Command;
import server.controller.Notifier;
import shared.model.Task;

import java.util.List;

public class CommandProcessor {
    private Command command;
    private CommandProcessorHandler handler;

    public CommandProcessor(Command command){
        this.command = command;
        handler = new GetAllClientHandler(command);
        handler.linkWith(new GetNotificationHandler(command)).linkWith(new GetNotificationHandler(command)).linkWith(new GetErrorHandler(command));
    }

    public void chooseActivity(){
        handler.check(command);
        /*switch (command.getCommandId()){
            case(0): getAll();
            break;
            case(1): getNotification();
            break;
            case(99): getError();
            break;
        }*/
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
