package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import controller.Notification;
import javafx.util.Callback;
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

/**
 * This is controller of NotificationWindow
 *
 * @see NotificationWindow
 */

public class NotificationController {

    private Notification notification;
    @FXML
    private Label mainLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Button deferButton;
    @FXML
    private Button finishButton;
    @FXML
    private Label setDateLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField hoursNewTextField;
    @FXML
    private TextField minutesNewTextField;
    @FXML
    private Label pointsLabel;
    @FXML
    private Button deferTaskButton;
    @FXML
    private Button fiveMinutesButton;
    @FXML
    private Button tenMinutesButton;
    @FXML
    private Button fifteenMinutesButton;
    @FXML
    private Label chooseTimeLabel;
    @FXML
    private Button chooseTimeButton;
    @FXML
    private Button cancelButton;


    public NotificationController(Notification notification) {
        this.notification = notification;
    }

    public NotificationController() {
    }

    /**
     * notificatioon field change function
     * @param notification
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    /**
     * description label changing function
     */
    public void setLabel() {
        descLabel.setText(notification.getTask().getName() + " ." + notification.getTask().getDescription());
    }

    /**
     * Function for finish task in notification window
     * @param actionEvent
     */
    @FXML
    public void finishTaskAction(ActionEvent actionEvent) {
        Task finishTask = notification.getTask();
        finishTask.setStatus(Status.COMPLETED);
        finishTask.setDateOfDone(notification.getTask().getPlannedDate());
        Controller.getInstance().changeTask(notification.getTask().getId(), finishTask);
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
        MainWindowController.getInstance().refresh();
    }

    /**
     * Function for display UI required for defer task
     * @param actionEvent
     */
    @FXML
    public void deferButtonAction(ActionEvent actionEvent) {
        descLabel.setVisible(false);
        finishButton.setVisible(false);
        deferButton.setVisible(false);
        //deferTaskButton.setVisible(true);
        chooseTimeButton.setVisible(true);
        chooseTimeLabel.setVisible(true);
        fiveMinutesButton.setVisible(true);
        tenMinutesButton.setVisible(true);
        fifteenMinutesButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    /**
     * Function for defer task for 5 minutes
     * @param actionEvent
     */
    @FXML
    public void fiveMinutesButtonActive(ActionEvent actionEvent) {
        LocalDateTime dateNow = LocalDateTime.now().plusMinutes(5);
        Task newTask = notification.getTask();
        newTask.setPlannedDate(dateNow);
        newTask.setStatus(Status.DEFERRED);
        Controller.getInstance().changeTask(notification.getTask().getId(), newTask);
        Stage stage = (Stage) fiveMinutesButton.getScene().getWindow();
        stage.close();
        MainWindowController.getInstance().refresh();
    }

    /**
     * Function for defer task for 10 minutes
     * @param actionEvent
     */
    @FXML
    public void tenMinutesButtonActive(ActionEvent actionEvent) {
        LocalDateTime dateNow = LocalDateTime.now().plusMinutes(10);
        Task newTask = notification.getTask();
        newTask.setPlannedDate(dateNow);
        newTask.setStatus(Status.DEFERRED);
        Controller.getInstance().changeTask(notification.getTask().getId(), newTask);
        Stage stage = (Stage) fiveMinutesButton.getScene().getWindow();
        stage.close();
        MainWindowController.getInstance().refresh();
    }

    /**
     * Funtion for defer task for 15 minutes
     * @param actionEvent
     */
    @FXML
    public void fifteenMinutesButtonAction(ActionEvent actionEvent) {
        LocalDateTime dateNow = LocalDateTime.now().plusMinutes(15);
        Task newTask = notification.getTask();
        newTask.setPlannedDate(dateNow);
        newTask.setStatus(Status.DEFERRED);
        Controller.getInstance().changeTask(notification.getTask().getId(), newTask);
        Stage stage = (Stage) fiveMinutesButton.getScene().getWindow();
        stage.close();
        MainWindowController.getInstance().refresh();
    }

    /**
     * Funtion for display UI required for defer task time specified by user
     * @param actionEvent
     */
    @FXML
    public void chooseTimeButtonAction(ActionEvent actionEvent) {
        cancelButton.setVisible(false);
        chooseTimeButton.setVisible(false);
        chooseTimeLabel.setVisible(false);
        fiveMinutesButton.setVisible(false);
        tenMinutesButton.setVisible(false);
        fifteenMinutesButton.setVisible(false);
        setDateLabel.setVisible(true);
        datePicker.setVisible(true);
        hoursNewTextField.setVisible(true);
        pointsLabel.setVisible(true);
        minutesNewTextField.setVisible(true);
        deferTaskButton.setVisible(true);

    }

    /**
     * Function for defer task for time specified by user
     * @param actionEvent
     */
    public void deferTaskButtonAction(ActionEvent actionEvent) {
        LocalDateTime dateFromDatePicker = LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursNewTextField.getText()), Integer.parseInt(minutesNewTextField.getText()));
        if (dateFromDatePicker.isAfter(LocalDateTime.now())) {
            Task deferTask = notification.getTask();
            deferTask.setPlannedDate(LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursNewTextField.getText()), Integer.parseInt(minutesNewTextField.getText())));
            deferTask.setStatus(Status.DEFERRED);
            Controller.getInstance().changeTask(notification.getTask().getId(), deferTask);
            Stage stage = (Stage) deferButton.getScene().getWindow();
            stage.close();
            MainWindowController.getInstance().refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(NotificationControllerConstants.ALERT_TITLE);
            alert.setHeaderText(NotificationControllerConstants.ALERT_HEADER_TEXT);
            alert.setContentText(NotificationControllerConstants.ALERT_CONTEXT_TEXT);
            alert.showAndWait();
        }
    }

    /**
     * Function for limiting entered date on datePicker field
     * @return
     */
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

    /**
     * Function for initialize datePicker, hoursNewTextField and minutesNewTextField fields with their limits entered information
     */
    private void initItems() {
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        datePicker.setDayCellFactory(dayCellFactory);

        hoursNewTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));

        minutesNewTextField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
    }

    @FXML
    public void initialize() {
        initItems();
    }

}
