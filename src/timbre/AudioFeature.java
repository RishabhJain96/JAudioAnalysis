package timbre;

import decoder.Decoder;
import decoder.WavDecoder;

import java.io.IOException;

/**
 * Created by Rishabh on 12/19/15.
 */
public interface AudioFeature {
    public String title();
    public double[] calculateFeature(Decoder decoder, double samplingRate) throws IOException;
}
