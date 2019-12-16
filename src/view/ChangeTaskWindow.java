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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
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

        LocalDate date = new LocalDate(Controller.getInstance().getAll());
        datePicker.setValue();

        hoursTextField.setText(SelectedTasksController.getInstance().getRow().);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("changeTaskWindow.fxml"));
        stage.setTitle("CHANGE TASK");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickChange(ActionEvent actionEvent) {

    }

    public void clickDecline(ActionEvent actionEvent) {

    }
}
