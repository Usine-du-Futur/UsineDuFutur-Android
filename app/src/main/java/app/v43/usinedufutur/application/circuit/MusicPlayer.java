package app.v43.usinedufutur.application.circuit;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;

    private MusicPlayer() {
        // Private constructor to enforce singleton pattern
    }

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void playMusic(Context context, int resourceId) {
        stopMusic();
        mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
