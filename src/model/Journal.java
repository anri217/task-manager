package model;

import java.util.ArrayList;
import java.util.Date;

public class Journal {
    private ArrayList<Task> tasks;

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public Task getTaskByName(String name) {
        Task res = new Task();
        for (int i = 0; i < tasks.size(); ++i) {
            if (name == tasks.get(i).getName()) res = tasks.get(i);
        }
        return res;
    }

    public Task getTaskByDate(Date date) {
        Task res = new Task();
        for (int i = 0; i < tasks.size(); ++i) {
            if (date == tasks.get(i).getPlannedDate()) res = tasks.get(i);
        }
        return res;
    }

    /*public void setTask(String taskName, Task newTask) {
        Task task = getTaskByName(taskName);

        {
            task.setStatus(newTask.getStatus());
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setDatePlan(newTask.getDatePlan());
            task.setDateDone(newTask.getDateDone());
        }

    }*/

    public void changeStatus(String taskName, Status status) {
        getTaskByName(taskName).setStatus(status);
    }
}