package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import server.TaskConverter;
import server.Writer;
import server.controller.Controller;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.Handler;
import shared.JsonBuilder;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CancelTaskHandler implements Handler {

    @Override
    public void handle(Command command) throws IOException {
        ArrayList<Integer> ids = (ArrayList<Integer>) command.getContent();
        for (int i = 0; i < ids.size(); i++){
            Controller.getInstance().cancelTask(ids.get(i));
        }
        RefreshHelper.getInstance().getMainWindowController().refresh();
        Writer writer = Writer.getInstance();
        writer.sendCommand(createStringCommand());
        System.out.println("Сформированная команда после изменения задачи: "+createStringCommand()); // todo вместо вывода в консоль отправка клиенту строки
    }

    private String createStringCommand() throws JsonProcessingException {
        Command newCommand = CommandCreator.getInstance().createCommand(0, Controller.getInstance().getAll());
        JsonBuilder.getInstance().createJsonString(newCommand);
        String stringCommand = JsonBuilder.getInstance().createJsonString(newCommand);;
        return stringCommand;
    }
}
