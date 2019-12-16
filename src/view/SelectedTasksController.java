package view;

import model.Task;

public class SelectedTasksController {
    private static SelectedTasksController instance;
    private MainWindowRow row;

    private SelectedTasksController(){
        row = new MainWindowRow(new Task(0));
    }

    public static synchronized SelectedTasksController getInstance() {
        if (instance == null) {
            instance = new SelectedTasksController();
        }
        return instance;
    }

    public MainWindowRow getRow() {
        return row;
    }

    public void setRow(MainWindowRow row) {
        this.row = row;
    }
}
