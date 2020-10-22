package edu.up.facemaker;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

public class FaceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {

    private Face faceView;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;

    private int whichRadioButton;   //1 if on hair button, 2 if on eyes button, 3 if on skin button

    private static final int HAIR = 1;
    private static final int EYES = 2;
    private static final int SKIN = 3;

    public FaceController(Face faceView, SeekBar redSeekBar, SeekBar greenSeekBar, SeekBar blueSeekBar) {
        this.faceView = faceView;
        this.redSeekBar = redSeekBar;
        this.greenSeekBar = greenSeekBar;
        this.blueSeekBar = blueSeekBar;

        this.whichRadioButton = HAIR;
    }

    @Override
    public void onClick(View view) {
        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed to find a way to tell which button was pressed
         *
         * Resource:    https://stackoverflow.com/questions/3412180/how-to-determine-which-button-pressed-on-android
         * Solution:    I used their code but put it in a switch case instead
         */
        switch (view.getId()) {
            case R.id.randomFaceButton:
                faceView.randomizeFace();
                break;
            case R.id.hairRadioButton:
                redSeekBar.setProgress(Color.red(faceView.getHairColor()));
                greenSeekBar.setProgress(Color.green(faceView.getHairColor()));
                blueSeekBar.setProgress(Color.blue(faceView.getHairColor()));
                whichRadioButton = HAIR;
                break;
            case R.id.eyesRadioButton:
                redSeekBar.setProgress(Color.red(faceView.getEyeColor()));
                greenSeekBar.setProgress(Color.green(faceView.getEyeColor()));
                blueSeekBar.setProgress(Color.blue(faceView.getEyeColor()));
                whichRadioButton = EYES;
                break;
            case R.id.skinRadioButton:
                redSeekBar.setProgress(Color.red(faceView.getSkinColor()));
                greenSeekBar.setProgress(Color.green(faceView.getSkinColor()));
                blueSeekBar.setProgress(Color.blue(faceView.getSkinColor()));
                whichRadioButton = SKIN;
                break;
            default:
                break;
        }
        faceView.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //change which hairstyle to draw
        faceView.setHairStyle(pos);
        faceView.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //not using
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean userClicked) {
        //avoids the colors changing because the user presses a radio button
        if (!userClicked) {
            return;
        }
        Log.i("seekbar", "entered emthod");

        //sets the int color value only in the color of the SeekBar being used and which radio button is selected
        if (whichRadioButton == HAIR && seekBar.getId() == R.id.redSeekBar) {
            Log.i("seekbar", "changed red on hair");
            faceView.setHairColor(Color.argb(Face.MAX_RGB_VALUE, progress, Color.green(faceView.getHairColor()), Color.blue(faceView.getHairColor())));
        } else if (whichRadioButton == HAIR && seekBar.getId() == R.id.greenSeekBar) {
            faceView.setHairColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getHairColor()), progress, Color.blue(faceView.getHairColor())));
        } else if (whichRadioButton == HAIR && seekBar.getId() == R.id.blueSeekBar) {
            faceView.setHairColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getHairColor()), Color.green(faceView.getHairColor()), progress));
        } else if (whichRadioButton == EYES && seekBar.getId() == R.id.redSeekBar) {
            faceView.setEyeColor(Color.argb(Face.MAX_RGB_VALUE, progress, Color.green(faceView.getEyeColor()), Color.blue(faceView.getEyeColor())));
        } else if (whichRadioButton == EYES && seekBar.getId() == R.id.greenSeekBar) {
            faceView.setEyeColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getEyeColor()), progress, Color.blue(faceView.getEyeColor())));
        } else if (whichRadioButton == EYES && seekBar.getId() == R.id.blueSeekBar) {
            faceView.setEyeColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getEyeColor()), Color.green(faceView.getEyeColor()), progress));
        } else if (whichRadioButton == SKIN && seekBar.getId() == R.id.redSeekBar) {
            faceView.setSkinColor(Color.argb(Face.MAX_RGB_VALUE, progress, Color.green(faceView.getSkinColor()), Color.blue(faceView.getSkinColor())));
        } else if (whichRadioButton == SKIN && seekBar.getId() == R.id.greenSeekBar) {
            faceView.setSkinColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getSkinColor()), progress, Color.blue(faceView.getSkinColor())));
        } else if (whichRadioButton == SKIN && seekBar.getId() == R.id.blueSeekBar) {
            faceView.setSkinColor(Color.argb(Face.MAX_RGB_VALUE, Color.red(faceView.getSkinColor()), Color.green(faceView.getSkinColor()), progress));
        } else {
            return;
        }
        faceView.invalidate();
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
