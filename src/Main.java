import controller.Controller;
import controller.IOUtil;
import exceptions.BackupFileException;
import exceptions.PropertyParserInitException;
import model.Journal;
import model.Task;
import view.MainWindow;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException, ClassNotFoundException, PropertyParserInitException, BackupFileException {
    IOUtil ioUtil = IOUtil.getInstance();                             //begin
    Journal journal = (Journal)ioUtil.restoreFunction();
    Controller controller = Controller.getInstance();


    ioUtil.backupFunction(journal);                                   //end
  }
}