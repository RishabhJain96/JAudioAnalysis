package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AudioItem;
import timbre.AudioFeature;
import timbre.Frequency;
import timbre.SpectralCentroid;

import java.io.File;
import java.net.URL;
import java.util.*;

public class MainViewController extends Controller implements Initializable {
    @FXML
    MenuItem addFileMenuItem, fileCloseMenuItem;

    @FXML private Parent leftView;
    @FXML private LeftViewController leftViewController;

    @FXML private Parent graphView;
    @FXML private GraphViewController graphViewController;

    @FXML
    Menu functionMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: assert initializations here
        System.out.println("Initializing Main View Controller");

        AudioFeature frequency = new Frequency();

        Map<AudioFeature, EventHandler<ActionEvent>> allFeatures = new LinkedHashMap<>();
        allFeatures.put(frequency, event -> handleShowFrequency());

        for (AudioFeature f : allFeatures.keySet()) {
            MenuItem menuItem = new MenuItem(f.title());
            menuItem.setOnAction(allFeatures.get(f));
            functionMenu.getItems().add(menuItem);
        }
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

    public void handleShowFrequency() {
        manager.analyzeDecoders(new Frequency(), "Frequency", 44100);

    }
}
