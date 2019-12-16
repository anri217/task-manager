package view;

import controller.Controller;
import controller.TaskFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AddTaskWindowController {
    public TextField nameTextField;
    public TextArea descTextArea;
    public DatePicker datePicker;
    public TextField hoursTextField;
    public TextField minTextField;
    public Button addButton;
    public Button declineButton;

    public void clickAdd(ActionEvent actionEvent) throws Exception {
        TaskFactory factory = new TaskFactory();
        Controller.getInstance().addTask(factory.createTask(nameTextField.getText(), descTextArea.getText(), LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursTextField.getText()), Integer.parseInt(minTextField.getText())), Status.PLANNED));
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}