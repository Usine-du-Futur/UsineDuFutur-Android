package app.v43.usinedufutur.application.circuit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import app.v43.usinedufutur.R;
import app.v43.usinedufutur.application.GUIWelcome;
import app.v43.usinedufutur.arpack.DetectionTask;

/**
 * The activity used for handling circuits. From this activity, the user can chose/modify/delete
 * a existing circuit or create a new one.
 * @author Vivian Guy.
 */

public class GUIOptions extends Activity {

    /**
     * The logging tag. Useful for debugging.
     */
    private static String GUI_CIRCUIT_TAG = "GUICircuit";

    /**
     * The {@link View} holding  the existing {@link Circuit} stored on the phone.
     */
    private ListView existingCircuitsListView;

    /**
     * List of existing circuit in the folder Circuits of the internal storage saved as a {@link String} array [name, lap].
     */
    private ArrayList<String[]> existingCircuits;

    /**
     * The {@link CircuitAdapter} for the {@link GUIOptions#existingCircuitsListView}.
     */
    private ArrayAdapter adapter;

    /**
     * The item on the existingCircuitsListView selected by the user and the one previously selected.
     */
    private int selectedItem = -1;
    private int oldSelectedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_options);

        // Buttons in the GUI.
        Spinner musiqueSpinner = (Spinner) findViewById(R.id.musiqueSpinner);
        SeekBar musiqueSeekBar = (SeekBar) findViewById(R.id.musiqueSeekBar);
        SeekBar effetSeekBar = (SeekBar) findViewById(R.id.effetSeekBar);
        Button backBtn = (Button) findViewById(R.id.backBtn);

        LayoutInflater inflater = getLayoutInflater();

        /**
         * Button to launch the activity allowing the user to create a new circuit.
         */
        backBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });

    }

}
