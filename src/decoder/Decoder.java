package decoder;

import utils.Complex;

import java.io.IOException;

/**
 * Created by Rishabh on 12/19/15.
 */
public interface Decoder {
    /**
     * Decodes stream with given window size to prepare to retrieve data.
     *
     * @throws IOException
     */
    void decode() throws IOException;

    /**
     * Retrieves Complex data from Audio Stream for decoder
     *
     * @return Windowed complex data
     * @throws IOException
     */
    Complex[][] getData() throws IOException;
}
