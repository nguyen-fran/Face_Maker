package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.ColorInt;

import java.util.Random;

/**
 * A class that contains the data for the face that will be shown in the SurfaceView
 *
 * @author Francisco Nguyen
 */
public class Face extends SurfaceView {
    @ColorInt
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle; //identifies what hairstyle face has

    Paint skinColorPaint = new Paint();
    Paint eyeColorPaint = new Paint();
    Paint hairColorPaint = new Paint();

    Paint blackPaint = new Paint();
    Paint whitePaint = new Paint();

    private static final int MAX_RGB_VALUE = 255;
    private static final float faceMargin = 50.0f;
    private static final float faceHeight = 900.0f;
    private static final float faceWidth = 800.0f;
    private static final float eyeHeight = 100.0f;
    private static final float eyeWidth = 250.0f;
    private static final float irisRadius = 45.0f;
    private static final float pupilRadius = 22.5f;

    Paint red = new Paint();

    public Face(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        randomize();

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);

        blackPaint.setColor(0xFF000000);
        blackPaint.setStrokeWidth(4.0f);
        whitePaint.setColor(0xFFFFFFFF);
    }

    //sets instance variables to random values
    public void randomize() {
        Random rand = new Random();

        skinColor = Color.rgb(rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        eyeColor = Color.rgb(rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        hairColor = Color.rgb(rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
    }

    @Override
    public void onDraw(Canvas canvas) {
        float[] headCenter = {faceWidth / 2 + faceMargin, faceHeight / 2 + 2.5f * faceMargin};
        //drawing head
        canvas.drawOval(headCenter[0] - faceWidth / 2, headCenter[1] + faceHeight / 2, headCenter[0] + faceWidth / 2, headCenter[1] - faceHeight / 2, skinColorPaint);
        //drawing nose
        canvas.drawLine(headCenter[0], headCenter[1] -  faceMargin, headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, blackPaint);
        canvas.drawLine(headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, headCenter[0], headCenter[1] + faceMargin, blackPaint);
        //drawing mouth
        canvas.drawLine(headCenter[0] - faceWidth / 4, headCenter[1] + 4 * faceMargin, headCenter[0] + faceWidth / 4, headCenter[1] + 4 * faceMargin, blackPaint);

        drawEyes(canvas, headCenter);
    }

    private void drawEyes(Canvas canvas, float[] headCenter) {
        float[] leftEyeCenter = {headCenter[0] - faceWidth / 4, headCenter[1] - faceHeight / 6};
        float[] rightEyeCenter = {headCenter[0] + faceWidth / 4, headCenter[1] - faceHeight / 6};

        //drawing whites of eyes
        canvas.drawOval(leftEyeCenter[0] - eyeWidth / 2, leftEyeCenter[1] + eyeHeight / 2, leftEyeCenter[0] + eyeWidth / 2, leftEyeCenter[1] - eyeHeight / 2, whitePaint);
        canvas.drawOval(rightEyeCenter[0] - eyeWidth / 2, rightEyeCenter[1] + eyeHeight / 2, rightEyeCenter[0] + eyeWidth / 2, rightEyeCenter[1] - eyeHeight / 2, whitePaint);
        //drawing eyes' iris
        canvas.drawCircle(leftEyeCenter[0], leftEyeCenter[1], irisRadius, eyeColorPaint);
        canvas.drawCircle(rightEyeCenter[0], rightEyeCenter[1], irisRadius, eyeColorPaint);
        //drawing eyes' pupils
        canvas.drawCircle(leftEyeCenter[0], leftEyeCenter[1], pupilRadius, blackPaint);
        canvas.drawCircle(rightEyeCenter[0], rightEyeCenter[1], pupilRadius, blackPaint);

    }

}
