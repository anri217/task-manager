package server.view;

import server.view.mainWindow.MainWindowController;

/**
 * Class helping refresh table, stores server.controller
 */

public class RefreshHelper {
    private MainWindowController mainWindowController;
    private static RefreshHelper instance;

    private RefreshHelper() {
    }

    public static synchronized RefreshHelper getInstance() {
        if (instance == null) {
            instance = new RefreshHelper();
        }
        return instance;
    }


    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
