package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.Controller;
import server.controller.utils.Backupper;
import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;
import server.view.RefreshHelper;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;
import shared.view.AlertShowing;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Server extends Application {

    public static void main(String[] args) {
        Backupper backupper = new Backupper();
        Journal journal = new Journal();
        try {
            journal = (Journal) backupper.restoreFunction(1);
        } catch (BackupFileException | ClassNotFoundException | PropertyParserInitException e) {
            AlertShowing.showAlert(e.getMessage());
        }
        List<Task> tasks = journal.getAll();
        for (Task task : tasks) {
            if (task.getDateOfDone() == null) {
                if (task.getPlannedDate().isBefore(LocalDateTime.now())) {
                    task.setStatus(Status.OVERDUE);
                }
            }
            Controller.getInstance().addTask(task);
        }
        ServerFacade.getInstance().connect();
        Application.launch(args);
        Controller.getInstance().deleteAllNotification();
        try {
            backupper.backupFunction(Controller.getInstance().getJournal(), 1);
        } catch (PropertyParserInitException | BackupFileException e) {
            AlertShowing.showAlert(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/mainWindow/mainWindow.fxml"));
            stage.setTitle("TASK MANAGER");
            stage.setScene(new Scene(root));
            stage.show();
            RefreshHelper.getInstance().getMainWindowController().refresh();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ServerFacade facade = ServerFacade.getInstance();
        facade.setExit(false);
        facade.getServer().close();
    }
}
