package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import model.Task;

import java.util.ArrayList;

public class MainWindowRow {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty date;
    private SimpleObjectProperty<CheckBox> checkBox;

    public MainWindowRow(Task task) {
        name = new SimpleStringProperty(task.getName());
        description = new SimpleStringProperty(task.getDescription());
        date = new SimpleStringProperty(String.valueOf(task.getDatePlan()));
        checkBox = new SimpleObjectProperty<CheckBox>(new CheckBox());
    }

    public CheckBox getCheckBox() {
        return checkBox.get();
    }
}
