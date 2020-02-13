package server;

import client.CommandProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import server.controller.factories.TaskFactory;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.JsonParser;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) throws JsonProcessingException {
        /*Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));

        CommandCreator commandCreator = new CommandCreator();
        Command command = commandCreator.createCommand(1,task3);
        String jsonString = JsonBuilder.createJsonString(command);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        Task task = (Task)newCommand.getContent();
        System.out.println(task);*/
      //  test1();
       // test2();
      //  test3();
        testServerCommandProcessor();
    }

    public static void test1() throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));
        Controller.getInstance().addTask(task1);
        Controller.getInstance().addTask(task2);
        Controller.getInstance().addTask(task3);
        CommandCreator commandCreator = new CommandCreator();
        Command command = commandCreator.createCommand(0, Controller.getInstance().getAll());
        String jsonString = JsonBuilder.createJsonString(command);
        JsonParser parser = new JsonParser(jsonString);
        System.out.println(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        client.CommandProcessor commandProcessor = new CommandProcessor(newCommand);
        commandProcessor.chooseActivity();
    }

    public static void test2() throws JsonProcessingException {
        String message = "Hello world";
        CommandCreator commandCreator = new CommandCreator();
        Command command = commandCreator.createCommand(99 ,message);
        String jsonString = JsonBuilder.createJsonString(command);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
       // System.out.println(newCommand.getContent());
        client.CommandProcessor commandProcessor = new CommandProcessor(newCommand);
        commandProcessor.chooseActivity();
    }

    public static void test3() throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));

        CommandCreator commandCreator = new CommandCreator();
        Command command = commandCreator.createCommand(1,task3);
        String jsonString = JsonBuilder.createJsonString(command);
        System.out.println(jsonString);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        client.CommandProcessor commandProcessor = new CommandProcessor(newCommand);
        commandProcessor.chooseActivity();
    }

    public static void testServerCommandProcessor() throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        TaskFactory taskFactory = new TaskFactory();
        Task task3 = new Task(taskFactory.createTask(3, "Task3", "TaskDiscription", LocalDateTime.now(), Status.PLANNED));

        CommandCreator commandCreator = new CommandCreator();
        Command command = commandCreator.createCommand(1,task3);
        String jsonString = JsonBuilder.createJsonString(command);
        System.out.println(jsonString);
        JsonParser parser = new JsonParser(jsonString);
        parser.parseCommand();
        Command newCommand = parser.getCommand();
        server.CommandProcessor serverCommandProcessor = new server.CommandProcessor(newCommand);
        serverCommandProcessor.chooseActivity();
    }
}
