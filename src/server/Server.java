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

import java.time.LocalDateTime;
import java.util.List;

public class Server extends Application {
    public static void main(String[] args) throws BackupFileException, PropertyParserInitException, ClassNotFoundException {
        try {
            Backupper backupper = new Backupper();
            Journal journal = (Journal) backupper.restoreFunction(1);
            List<Task> tasks = journal.getAll();
            for (Task task : tasks) {
                if (task.getDateOfDone() == null) {
                    if (task.getPlannedDate().isBefore(LocalDateTime.now())) {
                        task.setStatus(Status.OVERDUE);
                    }
                }
                Controller.getInstance().addTask(task);
            }
            MultiThreadServer multiThreadServer = new MultiThreadServer();
            multiThreadServer.start();
            Application.launch(args);
            Controller.getInstance().deleteAllNotification();
            backupper.backupFunction(Controller.getInstance().getJournal(), 1);
        } catch (BackupFileException e) {
            AlertShowing.showAlert(e.getMessage());
        } catch (PropertyParserInitException e) {
            AlertShowing.showAlert(e.getMessage());
        } catch (ClassNotFoundException e) {
            AlertShowing.showAlert(e.getMessage());
        } catch (Exception e) {
            AlertShowing.showAlert(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainWindow/mainWindow.fxml"));
        stage.setTitle("TASK MANAGER");
        stage.setScene(new Scene(root));
        stage.show();
        RefreshHelper.getInstance().getMainWindowController().refresh();
    }

}
