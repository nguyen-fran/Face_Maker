package edu.up.facemaker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

public class FaceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {

    private Face faceView;

    public FaceController(Face faceView) {
        this.faceView = faceView;
    }

    @Override
    public void onClick(View view) {
        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed to find a way to tell which button was pressed
         *
         * Resource:    https://stackoverflow.com/questions/3412180/how-to-determine-which-button-pressed-on-android
         * Solution:    I used
         */
        switch (view.getId()) {
            case R.id.randomFaceButton:
                faceView.randomize();
                break;
            case R.id.hairRadioButton:
                //something
                break;
            case R.id.eyesRadioButton:
                //something
                break;
            case R.id.skinRadioButton:
                //something
                break;
            default:
                break;
        }
        faceView.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not using
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not using
    }
}
