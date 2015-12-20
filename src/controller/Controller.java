package controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.stage.Stage;

public class Controller {
    protected Stage stage;

    public final void setStage(Stage stage) {
        this.stage = stage;
    }

    public void displayError(String message, Alert.AlertType type, Runnable callBack) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Alert alert = new Alert(type);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                        if (callBack != null)
                            callBack.run();
                    }
                });
                alert.showAndWait();
            }
        });
    }
}
