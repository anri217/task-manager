package server;

import server.controller.Controller;
import server.model.Journal;
import server.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandController {

    private static Command command;
    private static int command_id;
    private static Map<Integer, Task> journal;

    public static void select_action(){
        switch (command_id){
            case(0):
                getAllToClient();
                break;
            case(1):
                addTask();
                break;
            case(2):
                deleteTask();
                break;
            case(3):
                changeTask();
                break;
            case(4):
                cancelTask();
                break;
        }
    }

    public static void cancelTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        Controller.getInstance().cancelTask(task.getId());
    }

    public static void changeTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task1 = tasks.get(0);
        Task task2 = tasks.get(1);
        Controller.getInstance().changeTask(task1.getId(), task2);
    }

    public static void deleteTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        Controller.getInstance().deleteTask(task.getId());
    }


    public static void addTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        Controller.getInstance().addTask(task);
    }

    public static void getAllToClient(){
        journal = Controller.getInstance().getAll().stream().collect(Collectors.toMap(Task::getId, Task -> Task));
        System.out.println(journal);
    }

    public static void parse_Command(Command command){
        setCommand(command);
        setCommand_id(command.getCommand_id());
        setJournal(command.getTasks());
    }

    public static void setCommand_id(int command_id) {
        CommandController.command_id = command_id;
    }

    public static int getCommand_id() {
        return command_id;
    }

    public static void setJournal(Map<Integer, Task> journal) {
        CommandController.journal = journal;
    }

    public static Map<Integer, Task> getJournal() {
        return journal;
    }

    public static void setCommand(Command command) {
        CommandController.command = command;
    }

    public static Command getCommand() {
        return command;
    }

}
