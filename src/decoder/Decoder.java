package decoder;

import utils.Complex;

import java.io.IOException;

/**
 * Created by Rishabh on 12/19/15.
 */
public interface Decoder {
    void decode(int windowSize) throws IOException;
    Complex[][] getFFT();
}
