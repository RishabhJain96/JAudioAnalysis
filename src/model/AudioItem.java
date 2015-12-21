package model;

/**
 * Created by Rishabh on 12/20/15.
 */
public class AudioItem {
    public AudioType type;
    public String path;

    public AudioItem(AudioType type, String path) {
        this.type = type;
        this.path = path;
    }

    public String toString(){
        return this.path;
    }

    public enum AudioType {
        WAV, MP3;
    }
}
