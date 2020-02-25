package server.controller;

import server.MonoClientThread;
import server.ServerFacade;
import server.view.RefreshHelper;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Status;
import shared.model.Task;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for working with notifications
 */

public class Notification extends TimerTask {
    private Timer timer;
    private Task task;

    /**
     * Constructor for creating new notification
     *
     * @param task - task which associated with this notification
     */
    public Notification(Task task) {
        setTask(task);
        createTimer();
    }

    /**
     * Getting timer field function
     *
     * @return current timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * timer field initializaton function
     */
    public void createTimer() {
        this.timer = new Timer();
    }

    /**
     * Getting task field function
     *
     * @return task, which associated with this notification
     */
    public Task getTask() {
        return task;
    }

    /**
     * task field change function
     *
     * @param task, which associated with this notification
     */
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            if (ServerFacade.getInstance().getClientThreadMap().isEmpty()) {
                Controller.getInstance().getTask(this.task.getId()).setStatus(Status.OVERDUE);
                RefreshHelper.getInstance().getMainWindowController().refresh();
            } else {
                Command command = CommandCreator.getInstance().createCommand(1, this.task);
                HashMap<Integer, MonoClientThread> clients = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClientThreadMap();
                String entry = JsonBuilder.getInstance().createJsonString(command);
                for (int port : clients.keySet()) {
                    clients.get(port).sendCommand(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    /**
     * timer kill function for current notification
     */
    public void cancelTimer() {
        timer.cancel();
    }

    /**
     * Start-up timer function
     */
    public void startTask() {
        timer.schedule(this, Date.from(task.getPlannedDate().atZone(ZoneId.systemDefault()).toInstant()));
    }

}
