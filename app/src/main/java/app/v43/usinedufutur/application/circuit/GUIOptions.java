package app.v43.usinedufutur.application.circuit;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;


import app.v43.usinedufutur.R;
import app.v43.usinedufutur.application.sound.MusicPlayer;

/**
 * The activity used for handling options. From this activity, the user can chose the music among miscellaneous musics
 * Also, he modify the volume of the music.
 *
 * @author Mehdi Fellahi.
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


    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_options);

        // Buttons in the GUI.
        Spinner musiqueSpinner = findViewById(R.id.musiqueSpinner);
        SeekBar musiqueSeekBar = findViewById(R.id.musiqueSeekBar);
        SeekBar effetSeekBar = findViewById(R.id.effetSeekBar);
        Button backBtn = findViewById(R.id.backBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.stream(songs).map(song -> song.name).toArray(String[]::new));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        musiqueSpinner.setAdapter(adapter);

        // Set a listener for item selections
        musiqueSpinner.setSelection(0,false);
        musiqueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *
             * @param parentView The AdapterView where the selection happened
             * @param selectedItemView The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             *
             * Get the instance of the MusicPLayer clas which is responsible of playing music, according the the song selected
             */
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

            /**
             *
             * @param seekBar The SeekBar whose progress has changed
             * @param progress The current progress level. This will be in the range min..max where min
             *                 and max were set by {@link ProgressBar#setMin(int)} and
             *                 {@link ProgressBar#setMax(int)}, respectively. (The default values for
             *                 min is 0 and max is 100.)
             * @param fromUser True if the progress change was initiated by the user.
             *
             * Set the volume selected
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Change the volume based on seekbar progress
                setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        initialized = true;
    }

    // Other methods and code for the OptionGUI class

    /**
     *
     * @return the current volume of the phone.
     */
    private int getCurrentVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            return (int) ((currentVolume / (float) maxVolume) * 100);
        }
        return 0;
    }

    /**
     * Set the volume selected tp the phone volume manager.
     * @param progress current volume of the app
     */
    private void setVolume(int progress) {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int targetVolume = (int) ((progress / 100f) * maxVolume);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0);
        }
    }
}




