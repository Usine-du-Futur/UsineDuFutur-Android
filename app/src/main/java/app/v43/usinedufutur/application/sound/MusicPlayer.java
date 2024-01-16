package app.v43.usinedufutur.application.sound;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;

    protected MusicPlayer() {
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
        mediaPlayer.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
