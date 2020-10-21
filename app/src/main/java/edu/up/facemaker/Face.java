package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.ColorInt;

import java.util.Random;

import static android.graphics.Paint.Style.FILL;

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

    private Paint skinColorPaint = new Paint();
    private Paint eyeColorPaint = new Paint();
    private Paint hairColorPaint = new Paint();

    private Paint blackPaint = new Paint();
    private Paint whitePaint = new Paint();

    public static final int MAX_RGB_VALUE = 255;
    private static final float faceMargin = 50.0f;
    private static final float faceHeight = 900.0f;
    private static final float faceWidth = 800.0f;
    private static final float eyeHeight = 100.0f;
    private static final float eyeWidth = 250.0f;
    private static final float irisRadius = 45.0f;
    private static final float pupilRadius = 22.5f;

    public Face(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        randomizeColors();

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);
        hairColorPaint.setStyle(FILL);

        blackPaint.setColor(0xFF000000);
        blackPaint.setStrokeWidth(4.0f);
        whitePaint.setColor(0xFFFFFFFF);
    }

    /**
     * Sets skin, eye, and hair color to random color values
     */
    public void randomizeColors() {
        Random rand = new Random();

        skinColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        eyeColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        hairColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);
    }

    /**
     * Draws a face.
     *
     * @param canvas    the canvas to draw on
     */
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

    /**
     * Helper method for onDraw. Draws the eyes of the face.
     *
     * @param canvas        the canvas to draw on
     * @param headCenter    coordinates so that the eyes can be drawn relative to the center of the face
     */
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

    public int getHairColor() {
        return hairColor;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public int getSkinColor() {
        return skinColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
        hairColorPaint.setColor(this.hairColor);
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
        eyeColorPaint.setColor(this.eyeColor);
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
        skinColorPaint.setColor(this.skinColor);
    }
}
