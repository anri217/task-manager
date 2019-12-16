package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Notification;
import model.Status;
import model.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

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


    public NotificationController(Notification notification){
        this.notification = notification;
    }

    public NotificationController(){
    }

    public void setNotification(Notification notification){
        this.notification = notification;
    }
    public Stage createStage(Notification ntf) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("notificationWindow.fxml"));
        Stage stage = new Stage( StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        setNotification(ntf);
//        descLabel.setText(ntf.getTextAlert());
        return stage;
    }

    public void setLabel(){
        descLabel.setText(notification.getTextAlert());
    }

    @FXML
    public void finishTaskAction(ActionEvent actionEvent) {
        Task finishTask = notification.getTask();
        finishTask.setStatus(Status.FINISHED);
        finishTask.setDateOfDone(notification.getTask().getPlannedDate());
        Controller.getInstance().changeTask(notification.getTask().getId(), finishTask);
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void deferButtonAction(ActionEvent actionEvent){
        descLabel.setVisible(false);
        finishButton.setVisible(false);
        deferButton.setVisible(false);
        deferTaskButton.setVisible(true);
        setDateLabel.setVisible(true);
        datePicker.setVisible(true);
        hoursNewTextField.setVisible(true);
        pointsLabel.setVisible(true);
        minutesNewTextField.setVisible(true);
    }

    public void deferTaskButtonAction(ActionEvent actionEvent){
        Task deferTask = notification.getTask();
        deferTask.setPlannedDate(LocalDateTime.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(), Integer.parseInt(hoursNewTextField.getText()), Integer.parseInt(minutesNewTextField.getText())));
        Controller.getInstance().changeTask(notification.getTask().getId(), deferTask);
        Stage stage = (Stage) deferButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void initialize(){
    }
    /*@FXML
    public void initialize(){
        descLabel.setText(notification.getTextAlert());
    }*/
}
