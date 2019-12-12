package controller;

import model.Status;
import model.Task;

import java.util.Date;

// todo WTF? for what this interface? Does creation have different behavior?
public interface Factory {
    Task createTask();

    Task createTask(String text, String text1, Date date, Status planned);
}
