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
        date = new SimpleStringProperty(task.getPlannedDate().getMinute() + ":" + task.getPlannedDate().getHour() +
                " " + task.getPlannedDate().getDayOfMonth() + "." + task.getPlannedDate().getMonthValue() + "." +
                task.getPlannedDate().getYear());
//        date = new SimpleStringProperty(String.valueOf(task.getPlannedDate()));
        checkBox = new SimpleObjectProperty<>(new CheckBox());
        id = task.getId();
    }

    public MainWindowRow(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CheckBox getCheckBox() {
        return checkBox.get();
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}