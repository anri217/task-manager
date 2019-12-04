package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Status;
import model.Task;

import java.util.ArrayList;
import java.util.Date;

public class MainWindow extends Application {
    public Button addTask;

    public TableView taskTable;

    public TableColumn chooseColumn;
    public TableColumn nameColumn;
    public TableColumn descriptionColumn;
    public TableColumn dateColumn;

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
        Task newTask = new Task();
        newTask.setName("New iPhone");
        newTask.setStatus(Status.PLANNED);
        newTask.setDateDone(null);
        newTask.setDatePlan(new Date(2007, 1, 9, 9, 41));
        newTask.setDescription("Say something about new iPhone");
        newTask.setId(model.IdGenerator.getId());
        MainWindowRow row = new MainWindowRow(newTask);
        rows.add(row);
        taskTable = new TableView<MainWindowRow>(FXCollections.observableArrayList(rows));
        chooseColumn.setCellValueFactory(new PropertyValueFactory<MainWindowRow, CheckBox>("checkBox"));
        taskTable.getColumns().add(chooseColumn);
        nameColumn.setCellValueFactory(new PropertyValueFactory<MainWindowRow, String>("task"));
    }
}