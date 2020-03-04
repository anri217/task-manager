package client.handlers;

import client.view.MainWindowRow;
import client.view.RefreshHelper;
import shared.Command;
import shared.TaskConverter;
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
        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(taskConverter.convert(taskList.get(i)));
        }
        ArrayList<MainWindowRow> rows = new ArrayList<MainWindowRow>();
        for (Task task : tasks) {
            rows.add(new MainWindowRow(task));
        }
        RefreshHelper.getInstance().getMainWindowController().setRows(rows);
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }
}
