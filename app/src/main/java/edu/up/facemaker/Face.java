package edu.up.facemaker;

import java.util.Random;

/**
 * A class that contains the date for the face that will be shown in the SurfaceView
 *
 * @author Francisco Nguyen
 */
public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle; //identifies what hairstyle face has

    private final int MAX_RGB_VALUE = 255;

    public Face() {
        randomize();
    }

    //sets instance variables to random values
    public void randomize() {
        Random rand = new Random();

        skinColor = rand.nextInt(MAX_RGB_VALUE);
        eyeColor = rand.nextInt(MAX_RGB_VALUE);
        hairColor = rand.nextInt(MAX_RGB_VALUE);
        hairStyle = rand.nextInt(MAX_RGB_VALUE);
    }

}
