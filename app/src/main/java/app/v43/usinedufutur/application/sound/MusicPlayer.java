package app.v43.usinedufutur.application.sound;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * This class is responsible of playing the music
 */
public class MusicPlayer {
    // to get the same instance through different classes
    private static MusicPlayer instance;
    // music manager
    private MediaPlayer mediaPlayer;

    protected MusicPlayer() {
        // Private constructor to enforce singleton pattern
    }

    /**
     *
     * @return an instance of the MusicPlayer class.
     */
    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    /**
     * Plays the music selected
     * @param context
     * @param resourceId music to be played
     */
    public void playMusic(Context context, int resourceId) {
        MediaPlayer mp = play(context, resourceId);
        mp.setLooping(true);
    }

    /**
     *
     * @param context
     * @param resourceId music to be played
     * @return the mediaplyer instance of th right music selected
     */
    public MediaPlayer play(Context context, int resourceId) {
        stopMusic();
        mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.start();

        return mediaPlayer;
    }

    /**
     * stop the music when the user quit the app.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
