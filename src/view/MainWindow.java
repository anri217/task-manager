package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainWindow extends Application {
    public Button addTask;
    public TableView taskTable;

    private ArrayList rows = new ArrayList();

    public static void run(String args[]) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        stage.setTitle("TASK MANAGER");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void clickAddTask(ActionEvent actionEvent) {
        MainWindowRow row = new MainWindowRow();
        rows.add(row);
        taskTable = new TableView<MainWindowRow>(FXCollections.observableArrayList(rows));
    }
}