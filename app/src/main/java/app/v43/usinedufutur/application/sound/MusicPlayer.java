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
     * Same as {@link #play(Context, int)}, but loops the audio
     * @param context The {@link Context} to be used
     * @param resourceId The music identifier
     */
    public void playMusic(Context context, int resourceId) {
        MediaPlayer mp = play(context, resourceId);
        mp.setLooping(true);
    }

    /**
     * Plays an audio
     * @param context The {@link Context} to be used
     * @param resourceId The audio identifier
     * @return The {@link MediaPlayer} being used
     */
    public MediaPlayer play(Context context, int resourceId) {
        stopMusic();
        mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.start();

        return mediaPlayer;
    }

    /**
     * Stops playing, in case it is
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
