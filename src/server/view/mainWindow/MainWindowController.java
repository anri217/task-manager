package server.view.mainWindow;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.MonoClientThread;
import server.ServerFacade;
import server.controller.Controller;
import server.controller.utils.BinarySerializer;
import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;
import server.view.MainWindowRow;
import server.view.RefreshHelper;
import server.view.SelectedTasksController;
import server.view.addTaskWindow.AddTaskWindow;
import server.view.changeTaskWindow.ChangeTaskWindow;
import shared.Command;
import shared.CommandCreator;
import shared.JsonBuilder;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class for catch events from items on main window
 *
 * @see MainWindow
 */

public class MainWindowController implements Initializable {
    public Button addTask;
    public Button delTask;
    public Button changeTask;
    public Button cancelTask;

    public TableView<MainWindowRow> taskTable;

    public TableColumn<MainWindowRow, CheckBox> chooseColumn;
    public TableColumn<MainWindowRow, String> nameColumn;
    public TableColumn<MainWindowRow, String> descriptionColumn;
    public TableColumn<MainWindowRow, String> dateColumn;
    public TableColumn<MainWindowRow, String> statusColumn;

    public MenuItem saveJournal;
    public MenuItem downloadJournal;

    private ArrayList<MainWindowRow> rows = new ArrayList<>();

    /**
     * Refreshing table
     */

    public void refresh() {
        delTask.setDisable(true);
        changeTask.setDisable(true);
        cancelTask.setDisable(true);
        rows.clear();
        int length = Controller.getInstance().getAll().size();
        for (int i = 0; i < length; i++) {
            rows.add(new MainWindowRow(Controller.getInstance().getAll().get(i)));
            rows.get(i).getCheckBox().setOnAction(actionEvent -> {
                selectedCheckBox();
            });
        }
        taskTable.setItems(FXCollections.observableList(rows));
        RefreshHelper.getInstance().setMainWindowController(this);
    }

    /**
     * Set disable for buttons
     */

    private void selectedCheckBox() {
        int count = 0, plannedCount = 0;

        for (MainWindowRow row : rows) {
            if (row.getCheckBox().isSelected()) {
                ++count;
                if (Controller.getInstance().getTask(row.getId()).getStatus() != Status.PLANNED) {
                    plannedCount++;
                }
            }

        }

        if (count == 0) {
            delTask.setDisable(true);
            changeTask.setDisable(true);
            cancelTask.setDisable(true);
        }

        if (count == 1) {
            delTask.setDisable(false);
            changeTask.setDisable(false);
            cancelTask.setDisable(false);
        }

        if (count > 1) {
            delTask.setDisable(false);
            changeTask.setDisable(true);
            cancelTask.setDisable(false);
        }

        if (plannedCount != 0) {
            cancelTask.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumns();
    }

    /**
     * Initialization columns of table
     */

    private void initColumns() {
        chooseColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        RefreshHelper.getInstance().setMainWindowController(this);
    }

    /**
     * Action from pushing add button
     *
     * @param actionEvent
     * @throws Exception
     */

    @FXML
    public void clickAddTask(ActionEvent actionEvent) throws Exception {
        AddTaskWindow addTaskWindow = new AddTaskWindow();
        Stage stage = new Stage();
        addTaskWindow.start(stage);
        refresh();
    }

    /**
     * Action from pushing delete button
     *
     * @param actionEvent
     * @throws Exception
     */


    @FXML
    public void clickDelTask(ActionEvent actionEvent) {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length && length > 0; i++) {
            if (taskTable.getItems().get(i).getCheckBox().isSelected()) {
                Controller.getInstance().deleteTask(taskTable.getItems().get(i).getId());
                taskTable.getItems().remove(i);
                --i;
                --length;
            }
        }
        selectedCheckBox();
    }

    /**
     * Action from pushing change button
     *
     * @param actionEvent
     * @throws Exception
     */

    public void clickChangeTask(ActionEvent actionEvent) throws Exception {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length; i++) {
            if (taskTable.getItems().get(i).getCheckBox().isSelected()) {
                SelectedTasksController.getInstance().setRow(taskTable.getItems().get(i));
            }
        }
        ChangeTaskWindow changeWindow = new ChangeTaskWindow();
        Stage stage = new Stage();
        changeWindow.start(stage);
        refresh();
    }

    /**
     * Function saving journal by push button Save
     */


    public void saveJournal(ActionEvent actionEvent) throws PropertyParserInitException, IOException, BackupFileException {
        BinarySerializer.getInstance().serializeObject(Controller.getInstance().getJournal());
    }

    /**
     * Function download journal by push button Download
     *
     * @param actionEvent
     * @throws ClassNotFoundException
     * @throws BackupFileException
     * @throws PropertyParserInitException
     * @throws IOException
     */

    public void downloadJournal(ActionEvent actionEvent) throws ClassNotFoundException, BackupFileException,
            PropertyParserInitException, IOException {
        Journal journal1 = Controller.getInstance().getJournal();
        Journal journal2 = (Journal) BinarySerializer.getInstance().deserializeObject();
        List<Task> arr = journal2.getAll();
        for (int i = 0; i < arr.size(); ++i) {
            if(!(journal1.isTaskInJournal(arr.get(i).getId()))) {
                if (arr.get(i).getDateOfDone() == null){
                    if (arr.get(i).getPlannedDate().isBefore(LocalDateTime.now())){
                        arr.get(i).setStatus(Status.OVERDUE);
                    }
                }
                journal1.addTask(arr.get(i));
            }
        }
        Controller.getInstance().setJournal(journal1);
        Command command = CommandCreator.getInstance().createCommand(0, journal1.getAll());
        HashMap<Integer, MonoClientThread> map = (HashMap<Integer, MonoClientThread>) ServerFacade.getInstance().getClients();
        for(int port : map.keySet()) {
            map.get(port).sendCommand(JsonBuilder.getInstance().createJsonString(command));
        }
        refresh();
    }

    /**
     * Function cancelling tasks by push button Cancel
     *
     * @param actionEvent
     */

    public void clickCancelTask(ActionEvent actionEvent) {
        int length = taskTable.getItems().size();
        for (int i = 0; i < length; i++) {
            if (taskTable.getItems().get(i).getCheckBox().isSelected()) {
                Controller.getInstance().cancelTask(taskTable.getItems().get(i).getId());
            }
        }
        refresh();
    }
}
