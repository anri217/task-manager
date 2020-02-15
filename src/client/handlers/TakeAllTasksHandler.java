package client.handlers;

import client.view.RefreshHelper;
import client.view.mainWindow.MainWindowController;
import com.fasterxml.jackson.core.JsonProcessingException;
import server.TaskConverter;
import server.controller.factories.TaskFactory;
import shared.Command;
import shared.Handler;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TakeAllTasksHandler implements Handler {

    private Journal journal;

    @Override
    public void handle(Command command) throws JsonProcessingException {
        journal = new Journal();
        List<LinkedHashMap<String, Object>> taskList = (List)command.getContent();
        for (int i = 0; i < taskList.size(); i++){
           journal.addTask(TaskConverter.getInstance().convert(taskList.get(i)));
        }
        RefreshHelper helper = RefreshHelper.getInstance();
        helper.getMainWindowController().setJournal(journal);
        helper.getMainWindowController().refresh();
        //System.out.println(tasks);
        // todo рефреш таблицы тасками из списка tasks
    }

    private void createTask(LinkedHashMap<String, Object> map){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime plannedDate = LocalDateTime.parse((String)map.get("plannedDate"),formatter);
        TaskFactory taskFactory = new TaskFactory();
        Task task = taskFactory.createTask((int)map.get("id"), (String) map.get("name"),(String)map.get("description"),plannedDate,chooseStatus((String)map.get("status")));
        journal.addTask(task);
        System.out.println(task);
    }

    private Status chooseStatus(String status){
        if (status.equals("PLANNED"))
            return Status.PLANNED;
        else{
            if (status.equals("COMPLETED")) return Status.COMPLETED;
            else{
                if(status.equals("OVERDUE")) return Status.OVERDUE;
                else{
                    if (status.equals("CANCELED")) return Status.CANCELED;
                    else return Status.DEFERRED;
                }
            }
        }
    }
}
