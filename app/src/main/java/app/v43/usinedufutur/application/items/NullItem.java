package app.v43.usinedufutur.application.items;

import android.widget.ImageButton;

import app.v43.usinedufutur.R;
import app.v43.usinedufutur.application.DroneController;
import app.v43.usinedufutur.arpack.DetectionTask;

/**
 * Implementation of a null {@link Item}.
 * This item symbolizes the absence of item.
 * @author Matthieu Michel.
 */

public class NullItem extends Item {

    /**
     * Name of the {@link Item}.
     */
    private final static String NAME = "nullItem";

    /**
     * Default constructor of the class {@link NullItem}. (Matthieu Michel - 06/02/2017).
     */
    public NullItem() {
        super(NAME);
    }


    @Override
    public boolean useItem(DroneController droneController, DetectionTask.Symbol symbol) {
        return false;
    }

    @Override
    public void applyEffect(DroneController droneController) {
        super.applyEffect(droneController);
    }

    @Override
    public void assignResource(ImageButton ib) {
        ib.setImageResource(R.drawable.null_object);
    }
}
