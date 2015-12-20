package decoder;

/**
 * Created by Rishabh on 12/19/15.
 */
public class WindowFactory {
    public static HammingWindow HammingWindow(int windowSize) {
        return new HammingWindow(windowSize);
    }

    public static HammingWindow HammingWindow() {
        return new HammingWindow();
    }
}
