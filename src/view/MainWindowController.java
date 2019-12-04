package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Status;
import model.Task;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public Button addTask;
    public Button delTask;
    public Button changeTask;

    public TableView<MainWindowRow> taskTable;

    public TableColumn<MainWindowRow, CheckBox> chooseColumn;
    public TableColumn<MainWindowRow, String> nameColumn;
    public TableColumn<MainWindowRow, String> descriptionColumn;
    public TableColumn<MainWindowRow, String> dateColumn;

    public MenuItem saveJournal;
    public MenuItem downloadJournal;

    static ArrayList<MainWindowRow> rows = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumns();
    }

    private void initColumns() {
        chooseColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    public void clickAddTask(ActionEvent actionEvent) {
        Task newTask = new Task();
        newTask.setName("New iPhone");
        newTask.setStatus(Status.PLANNED);
        newTask.setDateDone(null);
        newTask.setDatePlan(new Date(2007 - 1900, 0, 9, 9, 41));
        newTask.setDescription("Say something about new iPhone");
        newTask.setId(model.IdGenerator.getId());

        MainWindowRow row = new MainWindowRow(newTask);
        rows.add(row);

        row.getCheckBox().setOnAction(actionEvent1 -> {
            int count = 0;

            for (int i = 0; i < MainWindowController.rows.size(); i++) {
                if(rows.get(i).getCheckBox().isSelected()){ ++count; }
            }

            if (count == 0){
                delTask.setDisable(true);
                changeTask.setDisable(true);
            }

            if(count == 1){
                delTask.setDisable(false);
                changeTask.setDisable(false);
            }

            if(count > 1){
                delTask.setDisable(false);
                changeTask.setDisable(true);
            }
        });

        taskTable.getItems().add(row);
    }
}