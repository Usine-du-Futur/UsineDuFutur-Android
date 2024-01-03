package app.v43.usinedufutur.application.circuit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
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

        String[] music_arr = {"Coconut mall","Gta 4","Mario Kart Double Dash","Mario Kart 8","Mario Kart Wii","Mario Kart Retro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, music_arr);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        musiqueSpinner.setAdapter(adapter);

        // Set a listener for item selections
        musiqueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display a Toast message with the selected music option
                Toast.makeText(getApplicationContext(), "Selected: " + music_arr[position], Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.coconut_mall);
                } else if (position == 1) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.gta_4);
                } else if (position == 2) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.mari_kart_double_dash);
                }
                else if (position == 3) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.mario_kart_8);
                }
                else if (position == 4) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.mario_kart_wii);
                }
                else if (position == 5) {
                    MusicPlayer.getInstance().playMusic(getApplicationContext(), R.raw.retro_mario_kart);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });



        backBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });



        // Set a listener for seekbar changes
        musiqueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Change the volume based on seekbar progress
                setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing here
            }
        });
    }

    // Other methods and code for the OptionGUI class

    private int getCurrentVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            return (int) ((currentVolume / (float) maxVolume) * 100);
        }
        return 0;
    }

    private void setVolume(int progress) {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int targetVolume = (int) ((progress / 100f) * maxVolume);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0);
        }
    }
}




