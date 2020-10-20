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

    String[] hairstyles = {"Bald", "Buzzcut", "Bowl cut", "Dreadlocks", "Man-bun", "Mullet"};

    private Spinner hairstyleSpinner;
    private Button randomFaceButton;
    private RadioButton hairRadioButton;
    private RadioButton eyesRadioButton;
    private RadioButton skinRadioButton;
    private SeekBar redSeekbar;
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

        redSeekbar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        faceView = findViewById(R.id.faceSurfaceView);
        faceController = new FaceController(faceView, redSeekbar, greenSeekBar, blueSeekBar);

        randomFaceButton = findViewById(R.id.randomFaceButton);
        randomFaceButton.setOnClickListener(faceController);

        hairRadioButton = findViewById(R.id.hairRadioButton);
        hairRadioButton.setOnClickListener(faceController);
        eyesRadioButton = findViewById(R.id.eyesRadioButton);
        eyesRadioButton.setOnClickListener(faceController);
        skinRadioButton = findViewById(R.id.skinRadioButton);
        skinRadioButton.setOnClickListener(faceController);

    }
}