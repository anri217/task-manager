package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import model.Task;

public class MainWindowRow {
    private SimpleObjectProperty<Task> task;
    private SimpleObjectProperty<CheckBox> checkBox;

    public MainWindowRow(Task task) {
        this.task = new SimpleObjectProperty<Task>(task);
        checkBox = new SimpleObjectProperty<CheckBox>(new CheckBox());
    }

    public CheckBox getCheckBox() {
        return checkBox.get();
    }

    public Task getTask() {
        return task.get();
    }

    public void setTask(Task task) {
        this.task.set(task);
    }
}
