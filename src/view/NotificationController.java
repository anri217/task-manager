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
 * Controller of NotificationWindow
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
    @FXML
    private Label nameTaskLabel;
    @FXML
    private Button cancelChooseTimeButton;

    public NotificationController() {
    }

    /**
     * notificatioon field change function
     *
     * @param notification - displayed notification
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    /**
     * description label changing function
     */
    public void setLabel() {
        nameTaskLabel.setText(notification.getTask().getName() + ".");
        descLabel.setText(notification.getTask().getDescription() + ".");
    }

    /**
     * Function for finish task in notification window
     *
     * @param actionEvent - button click event
     */
    @FXML
    public void finishTaskAction(ActionEvent actionEvent) {
        Task finishTask = notification.getTask();
        finishTask.setStatus(Status.COMPLETED);
        finishTask.setDateOfDone(LocalDateTime.now());
        Controller.getInstance().changeTask(notification.getTask().getId(), finishTask);
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

    /**
     * Function for display UI required for defer task
     *
     * @param actionEvent - button click event
     */
    @FXML
    public void deferButtonAction(ActionEvent actionEvent) {
        descLabel.setVisible(false);
        finishButton.setVisible(false);
        deferButton.setVisible(false);
        chooseTimeButton.setVisible(true);
        chooseTimeLabel.setVisible(true);
        fiveMinutesButton.setVisible(true);
        tenMinutesButton.setVisible(true);
        fifteenMinutesButton.setVisible(true);
        cancelButton.setVisible(true);
    }

    /**
     * Function for defer task for 5 minutes
     *
     * @param actionEvent - button click event
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
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

    /**
     * Function for defer task for 10 minutes
     *
     * @param actionEvent - button click event
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
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

    /**
     * Funtion for defer task for 15 minutes
     *
     * @param actionEvent - button click event
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
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

    /**
     * Funtion for display UI required for defer task time specified by user
     *
     * @param actionEvent - button click event
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
        cancelChooseTimeButton.setVisible(true);
    }

    /**
     * Function for return previous UI with choice time
     *
     * @param actionEvent - button click event
     */
    @FXML
    public void cancelChooseTimeButtonAction(ActionEvent actionEvent) {
        cancelButton.setVisible(true);
        chooseTimeButton.setVisible(true);
        chooseTimeLabel.setVisible(true);
        fiveMinutesButton.setVisible(true);
        tenMinutesButton.setVisible(true);
        fifteenMinutesButton.setVisible(true);
        setDateLabel.setVisible(false);
        datePicker.setVisible(false);
        hoursNewTextField.setVisible(false);
        pointsLabel.setVisible(false);
        minutesNewTextField.setVisible(false);
        deferTaskButton.setVisible(false);
        cancelChooseTimeButton.setVisible(false);
    }

    /**
     * Function for defer task for time specified by user
     *
     * @param actionEvent - button click event
     */
    public void deferTaskButtonAction(ActionEvent actionEvent) {
        LocalDateTime dateFromDatePicker = LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(),
                Integer.parseInt(hoursNewTextField.getText()), Integer.parseInt(minutesNewTextField.getText()));
        if (dateFromDatePicker.isAfter(LocalDateTime.now())) {
            Task deferTask = notification.getTask();
            deferTask.setPlannedDate(LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(),
                    Integer.parseInt(hoursNewTextField.getText()), Integer.parseInt(minutesNewTextField.getText())));
            deferTask.setStatus(Status.DEFERRED);
            Controller.getInstance().changeTask(notification.getTask().getId(), deferTask);
            Stage stage = (Stage) deferButton.getScene().getWindow();
            stage.close();
            RefreshHelper.getInstance().getMainWindowController().refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(NotificationControllerConstants.ALERT_TITLE);
            alert.setHeaderText(NotificationControllerConstants.ALERT_HEADER_TEXT);
            alert.setContentText(NotificationControllerConstants.ALERT_CONTEXT_TEXT);
            alert.showAndWait();
        }
    }

    /**
     * Function for cancel click defer task button
     *
     * @param actionEvent - button click event
     */
    public void cancelButtonAction(ActionEvent actionEvent) {
        descLabel.setVisible(true);
        finishButton.setVisible(true);
        deferButton.setVisible(true);
        //deferTaskButton.setVisible(true);
        chooseTimeButton.setVisible(false);
        chooseTimeLabel.setVisible(false);
        fiveMinutesButton.setVisible(false);
        tenMinutesButton.setVisible(false);
        fifteenMinutesButton.setVisible(false);
        cancelButton.setVisible(false);
    }

    /**
     * Function for limiting entered date on datePicker field
     *
     * @return dayCellFactory
     */
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
                            setStyle("-fx-background-color: #ffc0cb;");
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
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(315);
        descLabel.setMaxHeight(120);
    }

    @FXML
    public void initialize() {
        initItems();
    }

}
