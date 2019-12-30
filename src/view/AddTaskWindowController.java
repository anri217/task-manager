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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ResourceBundle;

public class AddTaskWindowController implements Initializable {
    public TextField nameTextField;
    public TextArea descTextArea;
    public DatePicker datePicker;
    public TextField hoursTextField;
    public TextField minTextField;
    public Button addButton;
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
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        hoursTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));

        minTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
    }

    public void clickAdd(ActionEvent actionEvent) throws Exception {
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
        else {
            TaskFactory factory = new TaskFactory();
            Controller.getInstance().addTask(factory.createTask(nameTextField.getText(), descTextArea.getText(), cur,
                    Status.PLANNED));
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}