package model;

import java.util.ArrayList;
import java.util.Date;

public class Journal {
    private ArrayList<Task> tasks;//todo ArrayList to Map

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public Task getTaskByName(String name) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (name == tasks.get(i).getName()) res = tasks.get(i);
        }
        return res;
    }

    public Task getTaskByDate(Date date) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (date == tasks.get(i).getDatePlan()) res = tasks.get(i);
        }
        return res;
    }

    public void changeStatus(String taskName, Status status) {
        getTaskByName(taskName).setStatus(status);
    }
}