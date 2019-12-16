package model;

import idgenerator.IdGenerator;

import java.io.Serializable;
import java.util.*;

public class Journal implements Serializable {
    private Map<Integer, Task> tasks;

    public Journal() {
        tasks = new HashMap<Integer, Task>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }


    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task getTaskByName(String name) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (name.equals(tasks.get(i).getName())) res = tasks.get(i);
        }
        return res;
    }

    public Task getTaskByDate(Date date) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (date == tasks.get(i).getPlannedDate()) res = tasks.get(i);
        }
        return res;
    }

    public void changeTask(int id, Task task) {
        Task res = tasks.get(id);
        {
            res.setPlannedDate(task.getPlannedDate());
            res.setDateOfDone(task.getDateOfDone());
            res.setName(task.getName());
            res.setDescription(task.getDescription());
            res.setStatus(task.getStatus());
        }
    }

    public void changeStatus(String taskName, Status status) {
        getTaskByName(taskName).setStatus(status);
    }

    public ArrayList<Task> getAll() {
        Task[] arr = tasks.values().toArray(new Task[0]);
        return new ArrayList<>(Arrays.asList(arr));
    }
}