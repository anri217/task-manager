import controller.Controller;
import controller.util.IOUtil;
import exceptions.BackupFileException;
import exceptions.PropertyParserInitException;
import model.Journal;
import view.MainWindow;

public class Main {
    public static void main(String[] args) {
        try {
            IOUtil ioUtil = IOUtil.getInstance();
            Journal journal = (Journal) ioUtil.restoreFunction();
            Controller.getInstance().setJournal(journal);

            MainWindow.run(args);
            ioUtil.backupFunction(Controller.getInstance().getJournal());
        } catch (BackupFileException | PropertyParserInitException | ClassNotFoundException  e) {
            System.out.println(e.getMessage());
        }
    }
}