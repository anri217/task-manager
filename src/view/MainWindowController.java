package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.MainWindowRow;

import java.net.URL;
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

    private void selectedCheckBox(){
        int count = 0;

        for (MainWindowRow row : taskTable.getItems()) {
            if (row.getCheckBox().isSelected()) {
                ++count;
            }
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
    }

//    private void addTask(Task task){
//        MainWindowRow row = new MainWindowRow(task);
//        row.getCheckBox().setOnAction(actionEvent -> {
//            selectedCheckBox();
//        });
//        taskTable.getItems().add(row);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumns();
    }

    private void initColumns() {
        taskTable.setItems(FXCollections.observableList(Controller.getInstance().getRows()));

        chooseColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    public void clickAddTask(ActionEvent actionEvent) throws Exception {
        AddTaskWindow addTaskWindow = new AddTaskWindow();
        Stage stage = new Stage();
        addTaskWindow.start(stage);
//        selectedCheckBox();
        Stage stage1 = (Stage) addTask.getScene().getWindow();
        stage1.close();
    }

    @FXML
    public void clickDelTask(ActionEvent actionEvent) {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length; i++) {
            if(taskTable.getItems().get(i).getCheckBox().isSelected()){
                taskTable.getItems().remove(i);
                Controller.getInstance().getAll().remove(i);
                i--;
            }
        }
        selectedCheckBox();
    }
}