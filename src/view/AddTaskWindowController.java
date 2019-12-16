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

import java.util.Date;

public class AddTaskWindowController {
    public TextField nameTextField;
    public TextArea descTextArea;
    public DatePicker datePicker;
    public TextField hoursTextField;
    public TextField minTextField;
    public Button addButton;

    public void clickAdd(ActionEvent actionEvent) throws Exception {
        TaskFactory factory = new TaskFactory();
        Task task;
        task = factory.createTask(nameTextField.getText(), descTextArea.getText(), new Date(datePicker.getValue().getYear() - 1900, datePicker.getValue().getMonthValue(),  datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursTextField.getText()), Integer.parseInt(minTextField.getText())), Status.PLANNED);
        Controller.getInstance().addTask(task);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}