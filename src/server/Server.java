package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.Controller;
import server.controller.utils.Backupper;
import server.exceptions.BackupFileException;
import server.view.RefreshHelper;
import shared.constants.GeneralConstantsPack;
import shared.exceptions.PropertyParserInitException;
import shared.model.Journal;
import shared.view.AlertShowing;

import java.io.IOException;

public class Server extends Application {

    public static void main(String[] args) {
        Backupper backupper = new Backupper();
        Journal journal = new Journal();
        try {
            journal = (Journal) backupper.restoreFunction(Backupper.BIN);
        } catch (BackupFileException | ClassNotFoundException | PropertyParserInitException e) {
            AlertShowing.showAlert(e.getMessage());
        }
        Controller.getInstance().restoreTasks(journal);
        ServerFacade.getInstance().connect();
        Application.launch(args);
        try {
            backupper.backupFunction(Controller.getInstance().getJournal(), Backupper.BIN);
        } catch (PropertyParserInitException | BackupFileException e) {
            AlertShowing.showAlert(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(GeneralConstantsPack.PATH_TO_FXML));
            stage.setTitle(GeneralConstantsPack.WINDOW_NAME);
            stage.setScene(new Scene(root));
            stage.show();
            RefreshHelper.getInstance().getMainWindowController().refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ServerFacade facade = ServerFacade.getInstance();
        facade.setExit(true);
        facade.getServerSocket().close();
        if(ServerFacade.getInstance().isEmptyMap()) {
            System.exit(0);
        }
    }
}
