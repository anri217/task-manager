package client;

import server.Command;
import server.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommandController {
    private static Command command;
    private static int command_id;
    private static Map<Integer, Task> journal;

    public static void select_action(){
        switch (command_id){
            case(0):
                getAllFromServer();
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
            case(5):
                notification();
                break;
        }
    }

    public static void notification(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        //вызвать вызов окна оповещений.
    }

    public static void cancelTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        client.Controller.getInstance().cancelTask(task.getId());
    }

    public static void changeTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task1 = tasks.get(0);
        Task task2 = tasks.get(1);
        client.Controller.getInstance().changeTask(task1.getId(), task2);
    }

    public static void deleteTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        client.Controller.getInstance().deleteTask(task.getId());
    }


    public static void addTask(){
        List<Task> tasks = new ArrayList<Task>(journal.values());
        Task task = tasks.get(0);
        client.Controller.getInstance().addTask(task);
    }

    public static void getAllFromServer(){
        Iterator<Map.Entry<Integer, Task>> iterator = journal.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Task> task = iterator.next();
            Controller.getInstance().addTask(task.getValue());
        }
        System.out.println(Controller.getInstance().getAll());
    }

    public static void parse_Command(Command command){
        setCommand(command);
        setCommand_id(command.getCommand_id());
        setJournal(command.getTasks());
    }

    public static void setCommand_id(int command_id) {
        client.CommandController.command_id = command_id;
    }

    public static int getCommand_id() {
        return command_id;
    }

    public static void setJournal(Map<Integer, Task> journal) {
        client.CommandController.journal = journal;
    }

    public static Map<Integer, Task> getJournal() {
        return journal;
    }

    public static void setCommand(Command command) {
        client.CommandController.command = command;
    }

    public static Command getCommand() {
        return command;
    }

}
