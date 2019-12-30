package view;

import controller.Controller;
import controller.IOUtil;
import exceptions.BackupFileException;
import exceptions.PropertyParserInitException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Journal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    public TableColumn statusColumn;

    private ArrayList<MainWindowRow> rows = new ArrayList<>();

    private void refresh() {
        delTask.setDisable(true);
        changeTask.setDisable(true);
        rows.clear();
        int length = Controller.getInstance().getAll().size();
        for (int i = 0; i < length; i++) {
            rows.add(new MainWindowRow(Controller.getInstance().getAll().get(i)));
            rows.get(i).getCheckBox().setOnAction(actionEvent -> {
                selectedCheckBox();
            });
        }
        taskTable.setItems(FXCollections.observableList(rows));
    }

    private void selectedCheckBox(){
        int count = 0;

        for (MainWindowRow row : rows) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumns();
    }

    private void initColumns() {
        chooseColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    public void clickAddTask(ActionEvent actionEvent) throws Exception {
        AddTaskWindow addTaskWindow = new AddTaskWindow();
        Stage stage = new Stage();
//        stage.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.start(stage);
        refresh();
    }

    @FXML
    public void clickDelTask(ActionEvent actionEvent) {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length && length > 0; i++) {
            if(taskTable.getItems().get(i).getCheckBox().isSelected()){
                Controller.getInstance().deleteTask(taskTable.getItems().get(i).getId());
                taskTable.getItems().remove(i);
                --i;
                --length;
            }
        }
        selectedCheckBox();
    }

    public void clickChangeTask(ActionEvent actionEvent) throws Exception {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length; i++) {
            if(taskTable.getItems().get(i).getCheckBox().isSelected()){
                SelectedTasksController.getInstance().setRow(taskTable.getItems().get(i));
            }
        }
        ChangeTaskWindow changeWindow = new ChangeTaskWindow();
        Stage stage = new Stage();
        changeWindow.start(stage);
        refresh();
    }

    public void saveJournal(ActionEvent actionEvent) throws PropertyParserInitException, IOException, BackupFileException {
        IOUtil.getInstance().serializeObject(Controller.getInstance().getJournal());
    }

    public void downloadJournal(ActionEvent actionEvent) throws ClassNotFoundException, BackupFileException, PropertyParserInitException, IOException {
        Controller.getInstance().setJournal((Journal) IOUtil.getInstance().deserializeObject());
        refresh();
    }
}
