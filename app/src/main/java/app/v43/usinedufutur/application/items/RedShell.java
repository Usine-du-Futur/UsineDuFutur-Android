package app.v43.usinedufutur.application.items;

import android.util.Log;
import android.widget.ImageButton;

import app.v43.usinedufutur.R;
import app.v43.usinedufutur.application.DroneController;
import app.v43.usinedufutur.application.sound.PlayerPool;
import app.v43.usinedufutur.arpack.DetectionTask;
import app.v43.usinedufutur.arpack.GUIGame;

/**
 * Implementation of red shell {@link Item}.
 * @author Lucas Pascal.
 */

public class RedShell extends Item {
    /**
     * The logging tag. Useful for debugging.
     */
    private final static String ITEM_TAG = "ITEM";

    /**
     * Name of the {@link Item}.
     */
    private final static String NAME = "redshell";

    /**
     * Reference of the {@link GUIGame}.
     */
    private final GUIGame GUI_GAME;

    /**
     * Default constructor of the class {@link RedShell}.
     */
    public RedShell(GUIGame guiGame) {
        super(NAME);
        GUI_GAME = guiGame;
    }

    @Override
    public boolean useItem(DroneController droneController, DetectionTask.Symbol symbol) {
        Log.d(ITEM_TAG, "A red shell has been thrown!");
        return true;
    }

    @Override
    public void applyEffect(DroneController droneController) {
        super.applyEffect(droneController);

        Log.d(ITEM_TAG, "You've been hit by a red shell!");
        if (GUI_GAME != null) {
            GUI_GAME.GUI_GAME_HANDLER.sendEmptyMessage(GUIGame.ANIMATE_RED_SHELL);
        }
        droneController.spinningJump();
    }

    @Override
    public void playSound() {
        PlayerPool.getInstance().play(R.raw.sfx_red_shell);
    }

    @Override
    public void assignResource(ImageButton ib) {
        ib.setImageResource(R.drawable.redshell);
    }
}
