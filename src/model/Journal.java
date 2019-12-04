package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Journal {
    private Map<Integer, Task> tasks;

    public void addTask(Task task) {
        tasks.put(IdGenerator.getId(),task);
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

    public ArrayList<Task> getAll() {
        ArrayList<Task> tasksArr = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksArr.add(tasks.get(i));
        }
        return tasksArr;
    }
}