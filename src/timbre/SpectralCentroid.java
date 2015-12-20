package timbre;

import decoder.Decoder;
import decoder.Magnitude;
import decoder.WavDecoder;
import decoder.WindowFactory;
import utils.Complex;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Author: Rishabh Jain
 * Based on: https://en.wikipedia.org/wiki/Spectral_centroid
 */
public class SpectralCentroid implements AudioFeature {

    public static void main(String[] args) {
        String in = "/Users/Rishabh/Desktop/audio/test4.wav";
        try {
            WavDecoder decoder = new WavDecoder(new File(in), WindowFactory.HammingWindow());
            SpectralCentroid centroid = new SpectralCentroid();
            centroid.calculateFeature(decoder, 44100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double[] calculateFeature(Decoder decoder, double samplingRate) throws IOException {
        Complex[][] fft = decoder.getFFT();
        double[][] magnitudes = decoder.getMagnitudes();
        double[] ret = new double[fft.length];

        for (int i = 0; i < fft.length; i++) {
            double[] mags = magnitudes[i];
            double nSum = 0;
            double dSum = 0;

            for (int j = 1; j < mags.length; j++) {
                nSum += mags[j]*j*samplingRate/decoder.getWindowLength();
                dSum += mags[j];
            }

            //double fn = samplingRate/decoder.getWindowLength();
            ret[i] = nSum/dSum;
        }
        System.out.println(Arrays.toString(ret));
        return ret;
    }
}
