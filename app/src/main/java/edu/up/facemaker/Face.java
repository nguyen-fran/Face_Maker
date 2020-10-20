package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

/**
 * A class that contains the data for the face that will be shown in the SurfaceView
 *
 * @author Francisco Nguyen
 */
public class Face extends SurfaceView {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle; //identifies what hairstyle face has

    Paint skinColorPaint = new Paint();
    Paint eyeColorPaint = new Paint();
    Paint eyePupilPaint = new Paint();
    Paint eyeWhitePaint = new Paint();
    Paint hairColorPaint = new Paint();

    private static final int MAX_RGB_VALUE = 256;
    private static final float faceLeft = 100.0f;
    private static final float faceHeight = 1200.0f;
    private static final float faceWidth = 600.0f;
    private static final float eyeHeight = 150.0f;
    private static final float eyeWidth = 300.0f;

    public Face(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        randomize();

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        eyePupilPaint.setColor(0x000);
        eyeWhitePaint.setColor(0xFFF);
        hairColorPaint.setColor(hairColor);
    }

    //sets instance variables to random values
    public void randomize() {
        Random rand = new Random();

        skinColor = rand.nextInt(MAX_RGB_VALUE);
        eyeColor = rand.nextInt(MAX_RGB_VALUE);
        hairColor = rand.nextInt(MAX_RGB_VALUE);
        hairStyle = rand.nextInt(MAX_RGB_VALUE);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawLine(0.0f, 0.0f, 1000.0f, 1000.0f, eyePupilPaint);
        canvas.drawOval(faceLeft, faceHeight, faceLeft + faceWidth, 0, skinColorPaint);
        Log.i("onDraw", "entered onDraw");

    }

}
