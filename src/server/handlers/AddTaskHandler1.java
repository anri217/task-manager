package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.TaskConverter;
import server.Writer;
import server.controller.Controller;
import server.view.RefreshHelper;
import server.view.mainWindow.MainWindowController;
import shared.Handler;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Task;

import java.io.IOException;
import java.util.LinkedHashMap;

public class AddTaskHandler1 implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        Task task =  TaskConverter.getInstance().convert((LinkedHashMap<String, Object>)command.getContent());
        Controller.getInstance().addTask(task);
        RefreshHelper helper = RefreshHelper.getInstance();
        MainWindowController controller = helper.getMainWindowController();
        controller.refresh();
        Writer writer = Writer.getInstance();
        writer.sendCommand(createStringCommand());
        System.out.println(createStringCommand()); // todo вместо вывода в консоль - отправка клиенту той строки.
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);
        return stringCommand;
    }
}
