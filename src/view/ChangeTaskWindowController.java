package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class ChangeTaskWindowController implements Initializable {
    public TextField nameTextField;
    public TextArea descTextArea;
    public DatePicker datePicker;
    public TextField hoursTextField;
    public TextField minTextField;
    public Button changeButton;
    public Button declineButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumns();
    }

    private void initColumns() {
        nameTextField.setText(SelectedTasksController.getInstance().getRow().getName());

        descTextArea.setText(SelectedTasksController.getInstance().getRow().getDescription());

        LocalDateTime localDateTime = Controller.getInstance().getTask(SelectedTasksController.getInstance().getRow().getId()).getPlannedDate();
        datePicker.setValue(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth()));

        hoursTextField.setText(String.valueOf(date_.getHours()));

        minTextField.setText(String.valueOf(date_.getMinutes()));
    }

    public void clickChange(ActionEvent actionEvent) {
        //
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}
