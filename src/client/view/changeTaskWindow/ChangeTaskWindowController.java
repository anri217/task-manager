package client.view.changeTaskWindow;

import client.ClientFacade;
import client.view.MainWindowRow;
import client.view.RefreshHelper;
import client.view.SelectedTasksController;
import client.view.mainWindow.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import shared.Command;
import shared.CommandCreator;
import shared.CommandSender;
import shared.JsonBuilder;
import shared.factories.TaskFactory;
import shared.model.Status;
import shared.model.Task;
import shared.utils.IdGenerator;
import shared.view.AlertShowing;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class for catch events from items on ChangeTask window
 *
 * @see ChangeTaskWindow
 */

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

    /**
     * Initialization of some fields
     */

    private void initItems() {
        nameTextField.setText(SelectedTasksController.getInstance().getRow().getName());

        descTextArea.setText(SelectedTasksController.getInstance().getRow().getDescription());

        MainWindowController controller = RefreshHelper.getInstance().getMainWindowController();

        ArrayList<MainWindowRow> rows = controller.getRows();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm   dd.MM.yyyy");

        MainWindowRow row = new MainWindowRow();
        for (MainWindowRow mainWindowRow : rows) {
            if (mainWindowRow.getId() == SelectedTasksController.getInstance().getRow().getId()) {
                row = mainWindowRow;
            }
        }

        LocalDateTime localDateTime = LocalDateTime.parse(row.getDate(), formatter);

        datePicker.setValue(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth()));
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        hoursTextField.setText(String.valueOf(localDateTime.getHour()));

        minTextField.setText(String.valueOf(localDateTime.getMinute()));

        hoursTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));

        minTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
    }

    /**
     * Function changing task in table
     *
     * @param actionEvent
     */

    public void clickChange(ActionEvent actionEvent) throws IOException {
        MainWindowController controller = RefreshHelper.getInstance().getMainWindowController();
        ArrayList<MainWindowRow> rows = controller.getRows();
        if (datePicker.getValue() == null || hoursTextField.getText().length() == 0 || minTextField.getText().length() == 0) {
            AlertShowing.showAlert("ENTER DATA AND TIME");
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
                newTask.setId(SelectedTasksController.getInstance().getRow().getId());
                Command command = CommandCreator.getInstance().createCommand(3, newTask, ClientFacade.getInstance().getPort());
                String jsonString = JsonBuilder.getInstance().createJsonString(command);
                CommandSender.getInstance().sendCommand(jsonString);
                Stage stage = (Stage) changeButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    /**
     * Closing change task window
     *
     * @param actionEvent
     */

    public void clickDecline(ActionEvent actionEvent) {
        Stage stage = (Stage) declineButton.getScene().getWindow();
        stage.close();
    }
}
