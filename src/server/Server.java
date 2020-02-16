package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import server.controller.Controller;
import server.controller.utils.Backupper;
import server.controller.utils.BinarySerializer;
import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;
import server.view.RefreshHelper;
import server.view.mainWindow.MainWindow;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;

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
            showAlert(e.getMessage());
            throw new BackupFileException(e.getMessage());
        } catch (PropertyParserInitException e) {
            showAlert(e.getMessage());
            throw new PropertyParserInitException(e.getMessage());
        } catch (ClassNotFoundException e) {
            showAlert(e.getMessage());
            throw new ClassNotFoundException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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

    private static void showAlert(String message) {
        new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("ALERT");
                alert.setHeaderText(null);
                alert.setContentText(message);

                alert.showAndWait();
            }
        });
    }
}
