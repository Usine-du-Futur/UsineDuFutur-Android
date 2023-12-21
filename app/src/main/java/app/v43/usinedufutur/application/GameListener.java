package app.v43.usinedufutur.application;

import app.v43.usinedufutur.application.circuit.Circuit;
import app.v43.usinedufutur.application.items.Item;
import app.v43.usinedufutur.arpack.DetectionTask;

/**
 * Interface for the communication between {@link app.v43.usinedufutur.application.network.BluetoothCommunication}, {@link app.v43.usinedufutur.arpack.GUIGame} and {@link Game}.
 * Methods from this interface enable to notify the Bluetooth module and the user interface from the
 * first player's action.
 * @author Vivian Guy.
 */

public interface GameListener {

    /**
     * Called when the player is ready to start the race.
     */
    void onPlayerReady();

    /**
     * Called when the player has finished the race.
     */
    void onPlayerFinished();

    /**
     * Called when the player has finished a lap.
     */

    void onPlayerFinishedLap();

    /**
     * Called when the player use an {@link Item}.
     * @param item The item used.
     */
    void onPlayerUseItem(Item item, DetectionTask.Symbol lastMarkerSeen);

    /**
     * Called when the player give up the race.
     */
    void onPlayerGaveUp();

    /**
     * Called when an {@link Item} is touched on the {@link Circuit}.
     * @param item The item touched.
     */
    void onItemTouched(Item item, DetectionTask.Symbol symbol);

    /**
     * Called when the two players are ready and the start begins.
     */
    void onStartRace();

}
