package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) throws JsonProcessingException {
        Task task1 = new Task(0, "TaskName1", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        Controller.getInstance().addTask(task1);
        Controller.getInstance().addTask(task2);
        CommandCreator commandCreator = new CommandCreator(); //создаем экземпляр класса для создания команды
        Command command = commandCreator.createCommand(0, Controller.getInstance().getAll()); // создаем команду
        String stringCommand = JsonBuilder.createJsonString(command); // переводим команду в строку json
        System.out.println(stringCommand);
        client.CommandProcessor clientCommandProcessor = new client.CommandProcessor(command); // создаем CommandProcessor клиента
        clientCommandProcessor.chooseActivity(); // вызываем выбор действий по пришедшей команде
        command = commandCreator.createCommand(2, task2);
        stringCommand = JsonBuilder.createJsonString(command);
        System.out.println(stringCommand);
        server.CommandProcessor serverCommandProcessor = new server.CommandProcessor(command);
        serverCommandProcessor.chooseActivity();
    }
}
