package shared.view;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertShowing {
    public static void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERT");
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }
}
