package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import model.Task;

public class MainWindowRow {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty date;
    private SimpleObjectProperty<CheckBox> checkBox;
    private int id;

    public MainWindowRow(Task task) {
        name = new SimpleStringProperty(task.getName());
        description = new SimpleStringProperty(task.getDescription());
        date = new SimpleStringProperty(String.valueOf(task.getPlannedDate()));
        checkBox = new SimpleObjectProperty<>(new CheckBox());
        id = task.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CheckBox getCheckBox() {
        return checkBox.get();
    }
}