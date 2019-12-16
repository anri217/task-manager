package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Notification;

import java.io.IOException;

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
    public void initialize(){

    }
    /*@FXML
    public void initialize(){
        descLabel.setText(notification.getTextAlert());
    }*/
}
