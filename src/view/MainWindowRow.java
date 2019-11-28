package view;

import javafx.scene.control.CheckBox;
import model.Task;

public class MainWindowRow {
    private Task task;
    private CheckBox checkBox;

    public MainWindowRow() {
        task = new Task();
        checkBox = new CheckBox();
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
