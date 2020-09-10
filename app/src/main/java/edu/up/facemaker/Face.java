package edu.up.facemaker;

import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle; //identifies what hair style face has

    private final int MAX_RGB_VALUE = 255;

    public Face() {
        randomize();
    }

    public void randomize() {
        Random rand = new Random();

        skinColor = rand.nextInt(MAX_RGB_VALUE);
        eyeColor = rand.nextInt(MAX_RGB_VALUE);
        hairColor = rand.nextInt(MAX_RGB_VALUE);
        hairStyle = rand.nextInt(MAX_RGB_VALUE);
    }

}
