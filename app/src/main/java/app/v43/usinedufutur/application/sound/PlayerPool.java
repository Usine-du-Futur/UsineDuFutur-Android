package app.v43.usinedufutur.application.sound;

import android.content.Context;

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

    public int play(int resourceId) {
        return play(null, resourceId);
    }

    public int play(Context ctx, int resourceId) {
        if (ctx != null) {
            lastContext = ctx;
        } else {
            ctx = lastContext;
        }
        MusicPlayer player = players[index];
        player.playMusic(ctx, resourceId);

        int result = index;
        index = (index + 1) % count;

        return result;
    }

    public void stop(int index) {
        players[index].stopMusic();
    }

    public void stopAll() {
        for (MusicPlayer player : players) {
            player.stopMusic();
        }
    }
}
