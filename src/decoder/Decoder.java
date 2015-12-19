package decoder;

import utils.Complex;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rishabh on 12/19/15.
 */
public abstract class Decoder {
    protected AudioInputStream ais;
    protected Window window;
    protected double[] allData;
    protected Complex[][] cData;

    /**
     * Decodes stream with given window size to prepare to retrieve data.
     *
     * @throws IOException
     */
    abstract void decode() throws IOException;

    /**
     * Retrieves Complex data from Audio Stream for decoder. Decodes if necessary
     *
     * @return Windowed complex data
     * @throws IOException
     */
    public Complex[][] getData() throws IOException {
        if (cData == null) decode();
        return cData;
    }
}
