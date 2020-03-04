package server.view.addTaskWindow;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.controller.Controller;
import shared.factories.TaskFactory;
import shared.model.Status;
import shared.model.Task;
import shared.utils.IdGenerator;
import shared.view.AlertShowing;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;

/**
 * Class for catch events from items on AddTask window
 *
 * @see AddTaskWindow
 */

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

    /**
     * Initialization of some fields
     */

    private void initItems() {
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        hoursTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));

        minTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
    }

    /**
     * Function adding task in table
     * @param actionEvent
     * @throws Exception
     */

    public void clickAdd(ActionEvent actionEvent) throws Exception {
        if (datePicker.getValue() == null || hoursTextField.getText().length() == 0 || minTextField.getText().length() == 0){
            AlertShowing.showAlert("ENTER DATE AND TIME");
        } else {
            LocalDateTime cur = LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(),
                    datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursTextField.getText()),
                    Integer.parseInt(minTextField.getText()));
            if (nameTextField.getText().length() == 0) {
                AlertShowing.showAlert("ENTER NAME OF TASK");
            } else if (cur.isBefore(LocalDateTime.now())) {
                AlertShowing.showAlert("ENTER CORRECT TIME");
            } else {
                TaskFactory factory = new TaskFactory();
                Task newTask = new Task(factory.createTask(IdGenerator.getInstance().getId(), nameTextField.getText(),
                        descTextArea.getText(), cur, Status.PLANNED));
                if (Controller.getInstance().isTaskInJournal(newTask)){
                    AlertShowing.showAlert("THIS TASK ALREADY EXIST");
                }
                else {
                    Controller.getInstance().addTask(newTask);
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                }
            }
        }

    }

    /**
     * Closing add task window
     * @param actionEvent
     */

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}