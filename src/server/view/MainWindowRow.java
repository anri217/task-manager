package server.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import shared.model.Task;

/**
 * This is class for stores rows of table of tasks
 */

public class MainWindowRow {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty date;
    private SimpleObjectProperty<CheckBox> checkBox;
    private SimpleStringProperty status;
    private int id;

    public MainWindowRow(Task task) {
        name = new SimpleStringProperty(task.getName());
        description = new SimpleStringProperty(task.getDescription());
        String time;
        if (task.getPlannedDate().getHour() < 10 && task.getPlannedDate().getMinute() < 10) {
            time = "0" + task.getPlannedDate().getHour() + ":" + "0" + task.getPlannedDate().getMinute();
        } else if (task.getPlannedDate().getHour() < 10) {
            time = "0" + task.getPlannedDate().getHour() + ":" + task.getPlannedDate().getMinute();
        } else if (task.getPlannedDate().getMinute() < 10) {
            time = task.getPlannedDate().getHour() + ":" + "0" + task.getPlannedDate().getMinute();
        } else {
            time = task.getPlannedDate().getHour() + ":" + task.getPlannedDate().getMinute();
        }
        if (task.getPlannedDate().getMonthValue() < 10) {
            if (task.getPlannedDate().getDayOfMonth() < 10) {
                date = new SimpleStringProperty(time +
                        "   0" + task.getPlannedDate().getDayOfMonth() + ".0" + task.getPlannedDate().getMonthValue() + "." +
                        task.getPlannedDate().getYear());
            }
            else {
                date = new SimpleStringProperty(time +
                        "   " + task.getPlannedDate().getDayOfMonth() + ".0" + task.getPlannedDate().getMonthValue() + "." +
                        task.getPlannedDate().getYear());
            }
        }
        else {
            if (task.getPlannedDate().getDayOfMonth() < 10) {
                date = new SimpleStringProperty(time +
                        "   0" + task.getPlannedDate().getDayOfMonth() + "0" + task.getPlannedDate().getMonthValue() + "." +
                        task.getPlannedDate().getYear());
            }
            else {
                date = new SimpleStringProperty(time +
                        "   " + task.getPlannedDate().getDayOfMonth() + "0" + task.getPlannedDate().getMonthValue() + "." +
                        task.getPlannedDate().getYear());
            }
        }
        checkBox = new SimpleObjectProperty<>(new CheckBox());
        status = new SimpleStringProperty(task.getStatus().toString());
        id = task.getId();
    }

    public MainWindowRow() {
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

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}