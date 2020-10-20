package edu.up.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Face Maker app main driver
 *
 * @author Francisco Nguyen
 */
public class MainActivity extends AppCompatActivity {

    String[] hairstyles = {"Bald", "Buzzcut", "Man-bun", "Bowl cut", "Mullet", "Dreadlocks"};

    private Spinner hairstyleSpinner;
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

        faceView = findViewById(R.id.faceSurfaceView);
        faceController = new FaceController(faceView);

    }
}