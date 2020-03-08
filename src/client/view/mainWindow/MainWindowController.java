package client.view.mainWindow;

import client.view.MainWindowRow;
import client.view.RefreshHelper;
import client.view.SelectedTasksController;
import client.view.addTaskWindow.AddTaskWindow;
import client.view.changeTaskWindow.ChangeTaskWindow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shared.commandTools.Command;
import shared.commandTools.CommandCreator;
import shared.commandTools.CommandSender;
import shared.commandTools.JsonBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class for catch events from items on main window
 *
 * @see //MainWindow
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


    private ArrayList<MainWindowRow> rows;

    public ArrayList<MainWindowRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<MainWindowRow> rows) {
        this.rows = rows;
    }

    public MainWindowController() {
        this.rows = new ArrayList<>();
    }

    /**
     * Refreshing table
     */

    public void refresh() {
        delTask.setDisable(true);
        changeTask.setDisable(true);
        cancelTask.setDisable(true);
        for (MainWindowRow row : rows) {
            row.getCheckBox().setOnAction(actionEvent -> {
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
                if (!row.getStatus().equals("PLANNED")) {
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
    }

    /**
     * Action from pushing delete button
     *
     * @param actionEvent
     * @throws Exception
     */


    @FXML
    public void clickDelTask(ActionEvent actionEvent) throws IOException {
        int length = taskTable.getItems().size();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            if (taskTable.getItems().get(i).getCheckBox().isSelected()) {
                ids.add(taskTable.getItems().get(i).getId());
            }
        }
        Command command = CommandCreator.getInstance().createCommand(2, ids);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
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
    }

    /**
     * Function cancelling tasks by push button Cancel
     *
     * @param actionEvent
     */

    public void clickCancelTask(ActionEvent actionEvent) throws IOException {
        int length = taskTable.getItems().size();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            if (taskTable.getItems().get(i).getCheckBox().isSelected()) {
                ids.add(taskTable.getItems().get(i).getId());
            }
        }
        Command command = CommandCreator.getInstance().createCommand(4, ids);
        String jsonString = JsonBuilder.getInstance().createJsonString(command);
        CommandSender.getInstance().sendCommand(jsonString);
    }
}
