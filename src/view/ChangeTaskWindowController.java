package view;

import controller.Controller;
import controller.TaskFactory;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Status;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;

public class ChangeTaskWindowController implements Initializable {
    public TextField nameTextField;
    public TextArea descTextArea;
    public DatePicker datePicker;
    public TextField hoursTextField;
    public TextField minTextField;
    public Button changeButton;
    public Button declineButton;

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Disable Monday, Tuesday, Wednesday.
                        if (item.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
                            setDisable(true);
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initItems();
    }

    private void initItems() {
        nameTextField.setText(SelectedTasksController.getInstance().getRow().getName());

        descTextArea.setText(SelectedTasksController.getInstance().getRow().getDescription());

        LocalDateTime localDateTime = Controller.getInstance().getTask(SelectedTasksController.getInstance().getRow().getId()).getPlannedDate();

        datePicker.setValue(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth()));
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        hoursTextField.setText(String.valueOf(localDateTime.getHour()));

        minTextField.setText(String.valueOf(localDateTime.getMinute()));
    }

    public void clickChange(ActionEvent actionEvent) {
        TaskFactory taskFactory = new TaskFactory();
        Controller.getInstance().changeTask(SelectedTasksController.getInstance().getRow().getId(),
                taskFactory.createTask(nameTextField.getText(), descTextArea.getText(), LocalDateTime.of(datePicker.getValue().getYear(),
                        datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursTextField.getText()),
                        Integer.parseInt(minTextField.getText())), Status.PLANNED));
        Stage stage = (Stage) changeButton.getScene().getWindow();
        stage.close();
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}
