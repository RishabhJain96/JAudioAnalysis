package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AudioItem;
import timbre.Frequency;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends Controller implements Initializable {
    @FXML
    MenuItem addFileMenuItem, fileCloseMenuItem, showFrequencyGraphMenuItem;

    @FXML private Parent leftView;
    @FXML private LeftViewController leftViewController;

    @FXML private Parent graphView;
    @FXML private GraphViewController graphViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: assert initializations here
        System.out.println("Initializing Main View Controller");
    }

    public void passStage(Stage stage) {
        ControllerManager manager = new ControllerManager(this, leftViewController, graphViewController);
        this.setStageAndManager(stage, manager);
        leftViewController.setStageAndManager(stage, manager);
        graphViewController.setStageAndManager(stage, manager);
    }

    public void handleAddFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open audio file");
        File selected = fileChooser.showOpenDialog(stage);
        AudioItem item = new AudioItem(AudioItem.AudioType.WAV, selected.getPath());
        manager.addFile(item);
    }

    public void handleFileClose(ActionEvent event) {
        manager.clearFiles();
        manager.removeGraphs();
    }

    public void handleShowFrequency(ActionEvent event) {
        manager.analyzeDecoders(new Frequency(), "Frequency", 44100);

    }
}
