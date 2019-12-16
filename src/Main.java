import controller.IOUtil;
import javafx.application.Application;
import model.Journal;
import view.MainWindow;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
//
//    IOUtil ioUtil = IOUtil.getInstance();                             //begin
//
//    Journal journal = (Journal)ioUtil.restoreFunction();
//
//    ioUtil.backupFunction(journal);                                   //end
    MainWindow.run(args);
  }
}