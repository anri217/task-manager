package server;

import client.view.RefreshHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import server.view.mainWindow.MainWindowController;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Task;

public class CommandProcessor {
    private Command command;

    public CommandProcessor(Command command){
        this.command = command;
    }

    public void chooseActivity() throws JsonProcessingException {
        switch(command.getCommandId()){
            case(0): getAll();
            break;
            case(1): addTask();
            break;
            case(2): deleteTask();
            break;
            case(3): changeTask();
            break;
            case(4):cancelTask();
            break;
        }
    }

    private void cancelTask() throws JsonProcessingException {
        Task task = (Task)command.getContent();
        Controller.getInstance().cancelTask(task.getId());
        getAll();
    }

    private void addTask() throws JsonProcessingException {
        Task task = (Task)command.getContent();
        Controller.getInstance().addTask(task);
        MainWindowController controller = new MainWindowController();
        controller.refresh();
        //getAll();
    }

    private void deleteTask() throws JsonProcessingException {
        Task task = (Task)command.getContent();
        Controller.getInstance().deleteTask(task.getId());
        getAll();
    }

    private void changeTask() throws JsonProcessingException {
        Task task = (Task)command.getContent();
        Controller.getInstance().changeTask(task.getId(), task);
        getAll(); // сформировать команду "отдать все" клиенту и отправить ее
    }

    private void getAll() throws JsonProcessingException {
        Command command = new CommandCreator().createCommand(0, Controller.getInstance().getAll());
        String stringCommand = new JsonBuilder().createJsonString(command);
        System.out.println(stringCommand); //тестовая распечатка, что сформировалась правильная команда
        //отослать команду с журналом клиенту
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
