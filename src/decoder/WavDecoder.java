package decoder;

import utils.Complex;
import utils.FFT;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;


public class WavDecoder implements Decoder {
    private AudioInputStream ais;
    private double[] allData;
    private Complex[][] cData;

    private final double HAMMING_ALPHA = 0.53836;
    private final double HAMMING_BETA = 0.46164;

    public WavDecoder(File file) throws IOException, UnsupportedAudioFileException {
        ais  = AudioSystem.getAudioInputStream(file);
    }

    public WavDecoder(File file, int windowSize) throws IOException, UnsupportedAudioFileException {
        ais = AudioSystem.getAudioInputStream(file);
        decode(windowSize);
    }

    public Complex[][] getFFT() {
        return cData;
    }


    @Override
    public void decode(int windowSize) throws IOException {
        int size = ais.available();
        byte[] data = new byte[size];
        ais.read(data);
        allData = new double[data.length/2];

        ByteBuffer bb = ByteBuffer.wrap(data);
        if (ais.getFormat().isBigEndian()) {
            bb.order(ByteOrder.BIG_ENDIAN);
        } else {
            bb.order(ByteOrder.LITTLE_ENDIAN);
        }

        for (int i = 0; i < allData.length; i++) {
            double ret = (double)(bb.getShort());
            ret /= Short.MAX_VALUE;
            allData[i] = ret;
        }


        cData = new Complex[allData.length/windowSize][windowSize];
        for (int i = 0; i < cData.length; i++) {
            cData[i] = window(allData, i*windowSize, windowSize);
        }
    }

    private Complex[] window(double[] data, int pos, int windowSize) {
        // w(n) = alpha - Beta * cos(2πn/(N-1))
        Complex[] output = new Complex[windowSize];

        for (int i = pos; i < pos + windowSize; i++) {
            int j = i - pos; // go to 0
            double windowModifier = HAMMING_ALPHA - HAMMING_BETA*Math.cos((2*Math.PI*j)/(windowSize-1));
            //double windowModifier = 1;
            output[j] = new Complex(data[i]*windowModifier, 0);
        }

        Complex[] ret = FFT.fft(output);

        return ret;
    }

}
