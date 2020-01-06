package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import controller.Notification;
import model.Status;
import model.Task;

import java.time.LocalDateTime;

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

    public void setNotification(Notification notification) {
        this.notification = notification;
    }


    public void setLabel() {

        descLabel.setText(notification.getTask().getName() + " ." + notification.getTask().getDescription());
    }

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


    @FXML
    public void initialize() {
    }

}
