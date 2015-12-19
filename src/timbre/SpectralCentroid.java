package timbre;

import decoder.Decoder;
import decoder.Magnitude;
import utils.Complex;

import java.io.IOException;

/**
 * Author: Rishabh Jain
 * Based on: https://en.wikipedia.org/wiki/Spectral_centroid
 */
public class SpectralCentroid implements AudioFeature {
    @Override
    public double[] calculateFeature(Decoder decoder, double samplingRate) throws IOException {
        Complex[][] fft = decoder.getFFT();
        double[][] magnitudes = decoder.getMagnitudes();
        double[] ret = new double[fft.length];

        for (int i = 0; i < fft.length; i++) {
            double[] mags = magnitudes[i];
            double nSum = 0;
            double dSum = 0;
            // we only care about the positive frequencies
            for (int j = 1; j < mags.length/2; j++) {
                nSum += mags[j]*j;
                dSum += mags[j];
            }

            double fn = samplingRate/decoder.getWindowLength();
            ret[i] = fn * nSum / dSum;
        }

        return ret;
    }
}
