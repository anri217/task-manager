package view;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ChangeTaskWindow extends Application implements Initializable {
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

        Date date_ = new Date(String.valueOf(Controller.getInstance().getTask(SelectedTasksController.getInstance().getRow().getId()).getPlannedDate()));
        datePicker.setValue(LocalDate.of(date_.getYear(), date_.getMonth(), date_.getDay()));

        hoursTextField.setText(String.valueOf(date_.getHours()));

        minTextField.setText(String.valueOf(date_.getMinutes()));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("changeTaskWindow.fxml"));
        stage.setTitle("CHANGE TASK");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void clickChange(ActionEvent actionEvent) {
        //
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}
