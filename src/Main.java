import server.controller.Controller;
import server.controller.utils.BinarySerializer;
import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;
import shared.view.mainWindow.MainWindow;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    /*public static void main(String[] args) throws BackupFileException, PropertyParserInitException, ClassNotFoundException {
        try {
            BinarySerializer ioUtil = BinarySerializer.getInstance();
            Journal journal = (Journal) ioUtil.restoreFunction();
            List<Task> tasks = journal.getAll();
            for (Task task : tasks) {
                if (task.getDateOfDone() == null) {
                    if (task.getPlannedDate().isBefore(LocalDateTime.now())) {
                        task.setStatus(Status.OVERDUE);
                    }
                }
                Controller.getInstance().addTask(task);
            }
            MainWindow.run(args);
            Controller.getInstance().deleteAllNotification();
            ioUtil.backupFunction(Controller.getInstance().getJournal());
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
    }*/
}