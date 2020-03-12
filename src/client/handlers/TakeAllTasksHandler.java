package client.handlers;

import client.view.MainWindowRow;
import client.view.RefreshHelper;
import shared.commandTools.Command;
import shared.commandTools.TaskConverter;
import shared.model.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TakeAllTasksHandler implements Handler {

    @Override
    public void handle(Command command) {
        ArrayList<Task> tasks = new ArrayList<>();
        List<LinkedHashMap<String, Object>> taskList = (List) command.getContent();
        TaskConverter taskConverter = TaskConverter.getInstance();
        for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : taskList) {
            tasks.add(taskConverter.convert(stringObjectLinkedHashMap));
        }
        ArrayList<MainWindowRow> rows = new ArrayList<MainWindowRow>();
        for (Task task : tasks) {
            rows.add(new MainWindowRow(task));
        }
        RefreshHelper.getInstance().getMainWindowController().setRows(rows);
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }
}
