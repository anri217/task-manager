package server;

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
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
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
        System.out.println(task);
    }
}
