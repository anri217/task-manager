package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import shared.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandProcessor {
    private Command command;

    public CommandProcessor(Command command){
        this.command = command;
    }

    public void chooseActivity() throws JsonProcessingException {
        switch(command.getCommand_id()){
            case(0): getAll();
            break;
            case(1): addTask();
            break;
            case(2): deleteTask();
            break;
            case(3): changeTask();
            break;
        }
    }

    private void addTask() throws JsonProcessingException {
        Task task = (Task)command.getContent();
        Controller.getInstance().addTask(task);
        getAll();
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
