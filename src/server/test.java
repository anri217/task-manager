package server;

import client.CommandProcessor;
import client.view.notificationWindow.NotificationWindow;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Application;
import server.controller.Controller;
import server.controller.Notification;
import server.controller.factories.TaskFactory;
import server.view.mainWindow.MainWindow;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.JsonParser;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) throws Exception {
//      //  test1();
//        test2();
//        //test3();
//        //testn();
//       // testServerCommandProcessor();
        MainWindow.run(args);
    }

    public static void testn() throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));
        Command command = CommandCreator.getInstance().createCommand(1,task3);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        System.out.println(jsonString);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        server.CommandProcessor serverCommandProcessor = new server.CommandProcessor();
        serverCommandProcessor.processCommand(newCommand); // это важно. у всех контроллеров нужно вызывать именно метод processCommand.
    }

    public static void test1() throws Exception {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));
        Controller.getInstance().addTask(task1);
        Controller.getInstance().addTask(task2);
        Controller.getInstance().addTask(task3);
        Command command = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        JsonParser parser = new JsonParser(jsonString);
        System.out.println(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        client.CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processCommand(newCommand);
    }

    public static void test2() throws Exception {
        String message = "Hello world";
        Command command = CommandCreator.getInstance().createCommand(99, message);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
       // System.out.println(newCommand.getContent());
        client.CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processCommand(newCommand);
    }

    public static void test3() throws Exception {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));
        Command command = CommandCreator.getInstance().createCommand(1,task3);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        System.out.println(jsonString);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        client.CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.processCommand(newCommand);
    }

    public static void testServerCommandProcessor() throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));

        Command command = CommandCreator.getInstance().createCommand(1,task3);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        System.out.println(jsonString);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
       server.CommandProcessor serverCommandProcessor = new server.CommandProcessor();
       serverCommandProcessor.processCommand(newCommand);
    }
}
