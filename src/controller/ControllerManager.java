package controller;

import model.AudioItem;

/**
 * Created by Rishabh on 12/20/15.
 */
public class ControllerManager {
    private LeftViewController leftController;
    private MainViewController mainController;

    public ControllerManager(MainViewController main, LeftViewController left) {
        this.leftController = left;
        this.mainController = main;
    }

    public void addFile(AudioItem item) {
        leftController.addItem(item);
    }

    public void clearFiles() {
        leftController.clearFiles();
    }

    public void removeGraphs() {
        System.out.println("Will Remove Graphs");
    }

    public void showFrequencyGraph() {
        System.out.println("Will show frequency graph tab");
    }
}
