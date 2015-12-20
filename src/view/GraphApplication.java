package view;

import decoder.MP3Decoder;
import decoder.WavDecoder;
import decoder.WindowFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import timbre.AudioFeature;
import timbre.Frequency;
import timbre.SpectralCentroid;
import timbre.SpectralRolloff;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class GraphApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Spectral Centroid");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (samples)");
        //creating the chart
        final LineChart<Number,Number> lineChart =  new LineChart<Number,Number>(xAxis,yAxis);
        //final LineChart<Number,Number> lineChart2 =  new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Spectral Centroid");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Forest Gump");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Violin Concerto Exerpt");

        lineChart.setCreateSymbols(false);
        try {
            WavDecoder decoder = new WavDecoder(new File("/Users/Rishabh/Desktop/audio/test6.wav"), WindowFactory.HammingWindow(1024));
            WavDecoder decoder2 = new WavDecoder(new File("/Users/Rishabh/Desktop/audio/test4.wav"), WindowFactory.HammingWindow(1024));

            AudioFeature centroid = new SpectralCentroid();
            double[] data = centroid.calculateFeature(decoder, 44100);
            for (int i = 0; i < data.length; i++) {
                System.out.println("Setting Frequency: " + data[i]);
                series.getData().add(new XYChart.Data(i, data[i]));
            }

            double[] data2 = centroid.calculateFeature(decoder2, 44100);
            for (int i = 0; i < data2.length; i++) {
                series2.getData().add(new XYChart.Data(i, data2[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        lineChart.getData().add(series2);

        stage.setScene(scene);
        stage.show();
    }
}
