package shared.model;

import shared.utils.IdGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main shared.model class, which stores tasks
 *
 * @see Task
 */

public class Journal implements Serializable {
    private Map<Integer, Task> tasks;

    public Journal() {
        tasks = new HashMap<Integer, Task>();
    }

    /**
     * Add task in map
     *
     * @param task - new task
     */

    public void addTask(Task task) {
        while (tasks.containsKey(task.getId())) {
            task.setId(IdGenerator.getInstance().getId());
        }
        tasks.put(task.getId(), task);
    }

    /**
     * Delete task from map by id
     *
     * @param id - desired id
     */

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    /**
     * Getter function by id
     *
     * @param id - desired id
     */


    public Task getTask(int id) {
        return tasks.get(id);
    }

    /**
     * Getter function by name
     *
     * @param name - desired name
     */

    public Task getTaskByName(String name) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (name.equals(tasks.get(i).getName())) res = tasks.get(i);
        }
        return res;
    }

    /**
     * Getter function by date
     *
     * @param date - desired date
     * @return desired task
     */

    public Task getTaskByDate(LocalDateTime date) {
        Task res = null;
        for (int i = 0; i < tasks.size(); ++i) {
            if (date == tasks.get(i).getPlannedDate()) res = tasks.get(i);
        }
        return res;
    }

    /**
     * Change function by id
     *
     * @param id   - desired id
     * @param task - new task
     */

    public void changeTask(int id, Task task) {
        Task res = tasks.get(id);

        res.setPlannedDate(task.getPlannedDate());
        res.setDateOfDone(task.getDateOfDone());
        res.setName(task.getName());
        res.setDescription(task.getDescription());
        res.setStatus(task.getStatus());
    }


    public void changeStatus(String taskName, Status status) {
        getTaskByName(taskName).setStatus(status);
    }

    /**
     * @return unmodifiable list of all tasks
     */

    public List<Task> getAll() {
        Task[] arr = tasks.values().toArray(new Task[0]);
        return List.of(arr);
    }

    public boolean isTaskInJournal(Task task) {
        boolean res = false;
        List<Task> taskList = this.getAll();
        for (Task curTask : taskList) {
            res = (task.getPlannedDate().isEqual(curTask.getPlannedDate())) && (task.getStatus() == curTask.getStatus()) &&
                    (task.getName().equals(curTask.getName())) && (task.getDateOfDone() == curTask.getDateOfDone()) &&
                    (task.getDescription().equals(curTask.getDescription()));
            if (res) return true;
        }
        return false;
    }

    public boolean isTaskInJournal(int id) {
        return tasks.containsKey(id);
    }

    public int size() {
        return tasks.size();
    }
}