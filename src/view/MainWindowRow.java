package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import model.Task;

class MainWindowRow {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty date;
    private SimpleObjectProperty<CheckBox> checkBox;

    MainWindowRow(Task task) {
        name = new SimpleStringProperty(task.getName());
        description = new SimpleStringProperty(task.getDescription());
        date = new SimpleStringProperty(String.valueOf(task.getPlannedDate()));
        checkBox = new SimpleObjectProperty<CheckBox>(new CheckBox());
    }

    CheckBox getCheckBox() {
        return checkBox.get();
    }
}
