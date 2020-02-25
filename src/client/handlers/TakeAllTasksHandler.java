package client.handlers;

import client.view.MainWindowRow;
import client.view.RefreshHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import shared.TaskConverter;
import shared.factories.TaskFactory;
import shared.Command;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TakeAllTasksHandler implements Handler {

    private ArrayList<Task> tasks;

    @Override
    public void handle(Command command) {
        tasks = new ArrayList<>();
        List<LinkedHashMap<String, Object>> taskList = (List) command.getContent();
        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(TaskConverter.getInstance().convert(taskList.get(i)));
        }
        ArrayList<MainWindowRow> rows = new ArrayList<MainWindowRow>();
        for (Task task : tasks) {
            rows.add(new MainWindowRow(task));
        }
        RefreshHelper.getInstance().getMainWindowController().setRows(rows);
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }
}
