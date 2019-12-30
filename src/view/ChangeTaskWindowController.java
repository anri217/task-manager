package view;

import controller.Controller;
import controller.factories.TaskFactory;
import idgenerator.IdGenerator;
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

        hoursTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));

        minTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
    }

    public void clickChange(ActionEvent actionEvent) {
        LocalDateTime cur = LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(),
                datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursTextField.getText()),
                Integer.parseInt(minTextField.getText()));
        if(nameTextField.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText("Enter name of task");
            alert.showAndWait();
        }
        else if (cur.isBefore(LocalDateTime.now())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText("Enter correct time");
            alert.showAndWait();
        }
        else if (Controller.getInstance().getTask(SelectedTasksController.getInstance().getRow().getId()) == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText("This task has already deleted");
            alert.showAndWait();
            Stage stage = (Stage) changeButton.getScene().getWindow();
            stage.close();
        }
        else {
            TaskFactory taskFactory = new TaskFactory();
            Controller.getInstance().changeTask(SelectedTasksController.getInstance().getRow().getId(),
                    taskFactory.createTask(IdGenerator.getInstance().getId(), nameTextField.getText(), descTextArea.getText(), cur, Status.PLANNED));
            Stage stage = (Stage) changeButton.getScene().getWindow();
            stage.close();
        }
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}
