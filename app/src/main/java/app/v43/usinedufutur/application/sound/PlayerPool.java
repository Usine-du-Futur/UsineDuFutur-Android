package app.v43.usinedufutur.application.sound;

import android.content.Context;

/**
 * A class that handles multiple MusicPlayers.
 * Useful for when multiple sounds can be played at once.
 * By default, creates 4 players, but this value can be modified.
 */
public class PlayerPool {
    private static PlayerPool instance;

    public static PlayerPool getInstance(int count) {
        if (instance == null || instance.count < count) {
            instance = new PlayerPool(count);
        }
        return instance;
    }

    public static PlayerPool getInstance() {
        return getInstance(4);
    }

    private final int count;
    private int index = 0;
    private Context lastContext = null;

    private final MusicPlayer[] players;

    private PlayerPool(int count) {
        this.count = count;
        players = new MusicPlayer[count];
        for (int i = 0; i < count; i++) {
            players[i] = new MusicPlayer();
        }
    }

    /**
     * Players a song with a given {@param resourceId}.
     * Same as {@link #play(Context, int)}, but uses the last saved {@link Context}
     * @param resourceId The song identifier
     * @return A code for stopping this specific sound, used in {@link #stop}
     */
    public int play(int resourceId) {
        return play(null, resourceId);
    }

    /**
     * Same as {@link #play(int)}, but allows for specifying a {@link Context}, which is then saved.
     * @param ctx The {@link Context} to be used
     * @param resourceId The song identifier
     * @return A code for stopping this specific sound, used in {@link #stop}
     */
    public int play(Context ctx, int resourceId) {
        if (ctx != null) {
            lastContext = ctx;
        } else {
            ctx = lastContext;
        }
        MusicPlayer player = players[index];
        player.play(ctx, resourceId);

        int result = index;
        index = (index + 1) % count;

        return result;
    }

    /**
     * Stops the playing of a sound
     * @param index The code of the player to be stopped (obtained in {@link #play(int)})
     */
    public void stop(int index) {
        players[index].stopMusic();
    }

    /**
     * Stops the execution of all sounds
     */
    public void stopAll() {
        for (MusicPlayer player : players) {
            player.stopMusic();
        }
    }
}
