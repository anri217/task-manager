package controller;

import model.Status;
import model.Task;

import java.util.Date;

public interface Factory {
    Task createTask();

    Task createTask(String text, String text1, Date date, Status planned);
}
