package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.Controller;
import server.controller.utils.Backupper;
import server.controller.utils.BinarySerializer;
import server.exceptions.BackupFileException;
import server.exceptions.PropertyParserInitException;
import server.view.RefreshHelper;
import server.view.mainWindow.MainWindow;
import shared.model.Journal;

public class Server extends Application {
    public static void main(String[] args) throws BackupFileException, PropertyParserInitException, ClassNotFoundException {
        Backupper backupper = new Backupper(BinarySerializer.getInstance());
        //backupper.restoreFunction(1);
        MultiThreadServer multiThreadServer = new MultiThreadServer();
        multiThreadServer.start();
        Application.launch(args);
        backupper.backupFunction(Controller.getInstance().getJournal(), 1);
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
