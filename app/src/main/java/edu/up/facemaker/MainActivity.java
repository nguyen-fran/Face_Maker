package edu.up.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Face Maker app main driver
 *
 * @author Francisco Nguyen
 */
public class MainActivity extends AppCompatActivity {

    String[] hairstyles = {"Afro", "Bald", "Bowl cut", "Spikes"};

    private Spinner hairstyleSpinner;
    private Button randomFaceButton;
    private RadioButton hairRadioButton;
    private RadioButton eyesRadioButton;
    private RadioButton skinRadioButton;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;

    private Face faceView;
    private FaceController faceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * External Citation
         * Date:    9 September 2020
         * Problem: Couldn't figure out how to set up the spinner
         *
         * Resource:    https://learning.up.edu/moodle/mod/folder/view.php?id=905907
         * Solution:    I adopted Professor Nuxoll's framework.
         */
        hairstyleSpinner = findViewById(R.id.hairstyleSpinner);
        ArrayAdapter<String> hairstyleAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, this.hairstyles);
        hairstyleSpinner.setAdapter(hairstyleAdapter);

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed the radio buttons to change the seekbars' progress
         *
         * Resource:    https://learning.up.edu/moodle/pluginfile.php/1372746/mod_folder/content/0/ListenerExample.java?forcedownload=1
         * Solution:    I was reminded that the controller class could take in Views as parameters
         *              for its constructor and thereby have Views interact
         */
        faceView = findViewById(R.id.faceSurfaceView);
        faceController = new FaceController(faceView, redSeekBar, greenSeekBar, blueSeekBar);

        redSeekBar.setOnSeekBarChangeListener(faceController);
        greenSeekBar.setOnSeekBarChangeListener(faceController);
        blueSeekBar.setOnSeekBarChangeListener(faceController);

        randomFaceButton = findViewById(R.id.randomFaceButton);
        randomFaceButton.setOnClickListener(faceController);

        hairRadioButton = findViewById(R.id.hairRadioButton);
        hairRadioButton.setOnClickListener(faceController);
        eyesRadioButton = findViewById(R.id.eyesRadioButton);
        eyesRadioButton.setOnClickListener(faceController);
        skinRadioButton = findViewById(R.id.skinRadioButton);
        skinRadioButton.setOnClickListener(faceController);

        hairstyleSpinner.setOnItemSelectedListener(faceController);
    }
}