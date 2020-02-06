package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.controller.Controller;
import server.model.Status;
import server.model.Task;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) throws JsonProcessingException {
        Task task1 = new Task(0,"TaskName1","TaskDiscription", LocalDateTime.now(), Status.PLANNED);
        Task task2 = new Task(1, "TaskName2", "TaskDiscription", LocalDateTime.now(), Status.PLANNED);

        CommandCreator commandCreator = new CommandCreator(); //создаем экземпляр класса для создания команды
        commandCreator.setCommand_id(0); // задаем команде ее id
     //   commandCreator.addTask(task1);
      //  commandCreator.addTask(task2);
        commandCreator.CreateJson(); // создаем json строку из command_id и мапы, что есть в команде
        System.out.println(commandCreator.getCommand()); // распечатка, чтобы увидеть, что какая строчка создалась
        JsonParser jsonParser = new JsonParser(commandCreator.getCommand()); // создаем экземпляр нового парсера json и передаем ему строку в формате json команды
        jsonParser.parseCommand(); // парсим пришедшую команду
        System.out.println(jsonParser.getCommand().getCommand_id()); //распечатка, чтобы понять, что действительно хорошо парсится
        System.out.println(jsonParser.getCommand().getTasks()); // аналогично
        System.out.println(commandCreator.getJournal()); // смотрим, что у нас в создателе команд в мапе находится
      //  CommandController.setCommand(jsonParser.getCommand());
        CommandController.parse_Command(jsonParser.getCommand()); // забираем поля команды в поля command controller, чтобы с ними работать
        System.out.println(CommandController.getJournal()); // распечатать, что у нас в контроллер распарсилось из команды
        Controller.getInstance().addTask(task1); // добавим тестовые задачи в контроллер моделей сервера, чтобы потом их передать клиенту, так как мы тестируем команду "получить все", у которой command_id = 0
        Controller.getInstance().addTask(task2);
        CommandController.select_action(); // действие, которое выбирает, что необходимо сделать в соответствии и command_id
       // System.out.println(Controller.getInstance().getAll());
       // System.out.println(Controller.getInstance().getTask(0).getName());
        CommandCreator commandtoclient = new CommandCreator(); // создаем commandcreator для тестирования команд клиента
        commandtoclient.setCommand_id(0); // создаем команду "получить все" на стороне клиента
        commandtoclient.setJournal(CommandController.getJournal()); // отдаем в command creator журнал, который есть на сервере
        commandtoclient.CreateJson(); // создаем json строку
        jsonParser.setStringCommand(commandtoclient.getCommand()); // отдаем ее json парсеру, чтоб он перевел из строки в объект Command
        jsonParser.parseCommand();// парсим объект Command
        client.CommandController.parse_Command(jsonParser.getCommand()); // на клиентском command controoler распаршиваем пришедшую команду на command_id и map. - это имитация того, что клиенту пришла команда
        client.CommandController.select_action(); // выбираем, что нужно сделать, судя по command_id, который уже записан в CommandController
        System.out.println(client.Controller.getInstance().getTask(0).getName());// распечатываем показания из Controller моделей на стороне клиента, проверяя, верно ли записывается в журнал у клиента
    }
}
