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
import java.util.Arrays;
import java.util.stream.Stream;


import app.v43.usinedufutur.R;
import app.v43.usinedufutur.application.GUIWelcome;
import app.v43.usinedufutur.arpack.DetectionTask;

/**
 * The activity used for handling circuits. From this activity, the user can chose/modify/delete
 * a existing circuit or create a new one.
 *
 * @author Vivian Guy.
 */

public class GUIOptions extends Activity {
    static final Song[] songs = {
            new Song(R.raw.mario_kart_8, "Mario Kart 8"),
            new Song(R.raw.coconut_mall, "Coconut Mall"),
            new Song(R.raw.gta_4, "GTA 4"),
            new Song(R.raw.mari_kart_double_dash, "Mario Kart Double Dash"),
            new Song(R.raw.mario_kart_wii, "Mario Kart Wii"),
            new Song(R.raw.retro_mario_kart, "Retro Mario Kart"),
    };

    static private boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_options);

        // Buttons in the GUI.
        Spinner musiqueSpinner = findViewById(R.id.musiqueSpinner);
        SeekBar musiqueSeekBar = findViewById(R.id.musiqueSeekBar);
        SeekBar effetSeekBar = findViewById(R.id.effetSeekBar);
        Button backBtn = findViewById(R.id.backBtn);

        LayoutInflater inflater = getLayoutInflater();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.stream(songs).map(song -> song.name).toArray(String[]::new));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        musiqueSpinner.setAdapter(adapter);

        // Set a listener for item selections
        musiqueSpinner.setSelection(0,false);
        musiqueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (!initialized) return;

                // Display a Toast message with the selected music option
                Toast.makeText(getApplicationContext(), "Selected: " + songs[position].name, Toast.LENGTH_SHORT).show();

                MusicPlayer.getInstance().playMusic(getApplicationContext(), songs[position].id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        backBtn.setOnClickListener((view) -> GUIOptions.this.finish());


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

        initialized = true;
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




