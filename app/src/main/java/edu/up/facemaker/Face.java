package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
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
    private static final float afroRadius = 30.0f;

    private float[][] afroPoints;
    //the center point of the face that will be drawn on a surfaceview
    private float[] headCenter = {faceWidth / 2f + faceMargin, faceHeight / 2f + 5.0f * faceMargin};

    /**
     * constructor
     *
     * @param context
     * @param attributeSet
     */
    public Face(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        randomizeFace();

        hairStyle = 0;

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);
        hairColorPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setStrokeWidth(4.0f);
        whitePaint.setColor(Color.WHITE);

        //setting up balls for the afro hairstyle
        afroPoints = new float[750][2];
        setAfroBallCoords();
    }

    /**
     * Sets skin, eye, and hair color to random color values
     */
    public void randomizeFace() {
        Random rand = new Random();

        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed a way to store a color in an int
         *
         * Resource:    https://developer.android.com/reference/android/graphics/Color
         * Solution:    I learned to use Color.arbg() to encode rgb values as int. I later use
         *              Color.red(), Color.green(), Color.blue() to decode it in onSeekBarChange
         *              method in the FaceController class
         */
        skinColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        eyeColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));
        hairColor = Color.argb(MAX_RGB_VALUE, rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1), rand.nextInt(MAX_RGB_VALUE + 1));

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);

        hairStyle = rand.nextInt(4);
    }

    /**
     * Draws a face with a nose, a mouth, eyes, and hair
     *
     * @param canvas    the canvas to draw on
     */
    @Override
    public void onDraw(Canvas canvas) {
        //drawing head
        canvas.drawOval(headCenter[0] - faceWidth / 2f, headCenter[1] + faceHeight / 2f, headCenter[0] + faceWidth / 2f, headCenter[1] - faceHeight / 2f, skinColorPaint);
        //drawing nose
        canvas.drawLine(headCenter[0], headCenter[1] -  faceMargin, headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, blackPaint);
        canvas.drawLine(headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, headCenter[0], headCenter[1] + faceMargin, blackPaint);
        //drawing mouth
        canvas.drawLine(headCenter[0] - faceWidth / 4f, headCenter[1] + 4f * faceMargin, headCenter[0] + faceWidth / 4f, headCenter[1] + 4f * faceMargin, blackPaint);

        //drawing eyes
        drawEyes(canvas);

        //drawing hair
        switch (hairStyle) {
            case 0:
                drawAfro(canvas);
                break;
            case 1:
                break;
            case 2:
                drawBowlCutHair(canvas);
                break;
            case 3:
                drawSpikesHair(canvas);
                break;
            default:
                break;
        }
    }

    /**
     * Helper method for onDraw. Draws the eyes of the face.
     *
     * @param canvas    the canvas to draw on
     */
    private void drawEyes(Canvas canvas) {
        float[] leftEyeCenter = {headCenter[0] - faceWidth / 4f, headCenter[1] - faceHeight / 6f};
        float[] rightEyeCenter = {headCenter[0] + faceWidth / 4f, headCenter[1] - faceHeight / 6f};

        //drawing whites of eyes
        canvas.drawOval(leftEyeCenter[0] - eyeWidth / 2f, leftEyeCenter[1] + eyeHeight / 2f, leftEyeCenter[0] + eyeWidth / 2f, leftEyeCenter[1] - eyeHeight / 2f, whitePaint);
        canvas.drawOval(rightEyeCenter[0] - eyeWidth / 2f, rightEyeCenter[1] + eyeHeight / 2f, rightEyeCenter[0] + eyeWidth / 2f, rightEyeCenter[1] - eyeHeight / 2f, whitePaint);
        //drawing eyes' iris
        canvas.drawCircle(leftEyeCenter[0], leftEyeCenter[1], irisRadius, eyeColorPaint);
        canvas.drawCircle(rightEyeCenter[0], rightEyeCenter[1], irisRadius, eyeColorPaint);
        //drawing eyes' pupils
        canvas.drawCircle(leftEyeCenter[0], leftEyeCenter[1], pupilRadius, blackPaint);
        canvas.drawCircle(rightEyeCenter[0], rightEyeCenter[1], pupilRadius, blackPaint);
    }

    /**
     * Helper method for onDraw. Draws the bowl haircut option.
     *
     * @param canvas    the canvas to draw on
     */
    private void drawBowlCutHair(Canvas canvas) {
        //making the floats to use as params for a RectF object
        float topLeftX = headCenter[0] - (faceWidth / 2f);
        float topLeftY = headCenter[1] - (faceHeight / 2f) - (2f * faceMargin);
        float bottomRightX = headCenter[0] + (faceWidth / 2f);
        float bottomRightY = headCenter[1] + (2 * faceMargin);

        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed to draw an arc
         *
         * Resource:    https://developer.android.com/reference/android/graphics/Canvas#drawArc(android.graphics.RectF,%20float,%20float,%20boolean,%20android.graphics.Paint)
         * Solution:    I learned how to make a RectF and use it to draw an arc
         */
        RectF oval = new RectF(topLeftX, topLeftY, bottomRightX, bottomRightY);
        canvas.drawArc(oval, 180f, 180f, true, hairColorPaint);
    }

    /**
     * Helper method for onDraw. Draws the spiky hair option. Starts drawing the spikes at the bottom
     * left of the hair.
     *
     * @param canvas    the canvas to draw on
     */
    private void drawSpikesHair(Canvas canvas) {
        //size of hairstyle
        float hairWidth = faceWidth - (2.5f * faceMargin);
        float hairHeight = 8.5f * faceMargin;

        //coordinates for where to start the path
        float x = headCenter[0] - (faceWidth / 2f) + (1.25f * faceMargin);
        float y = headCenter[1] - (faceHeight / 4f);

        //number of spikes hair is made of
        int spikes = 10;

        /**
         * External Citation
         * Date:    20 October 2020
         * Problem: Needed to draw a unique polygon
         *
         * Resource:    https://developer.android.com/reference/android/graphics/Path
         * Solution:    I learned how to draw a unique polygon using moveTo() and lineTo()
         */
        Path hair = new Path();
        hair.moveTo(x, y);
        for (int i = 1; i < spikes * 2; i += 2) {
            hair.lineTo(x + (hairWidth * (i / (spikes * 2f))), y - hairHeight);
            //skipping last line the for loop would make to later draw a special final line to the path
            if (i + 1 >= spikes * 2)
                break;
            hair.lineTo(x + (hairWidth * ((i + 1) / (spikes * 2f))), y - hairHeight + 4.25f * faceMargin);
        }
        hair.lineTo(x + hairWidth, y);
        hair.close();

        canvas.drawPath(hair, hairColorPaint);;
    }

    /**
     * Helper method for onDraw. Draws the afro hairstyle option
     *
     * @param canvas    the canvas to draw on
     */
    private void drawAfro(Canvas canvas) {
        //drawing a circle at each point in afroPoints, with enough points this might look like an afro
        for (float[] point : afroPoints) {
            //some points are (0, 0) because setAfroPoints doesn't set every point in afroPoints to something
            if (point[0] != 0 && point[1] != 0) {
                canvas.drawCircle(point[0], point[1], afroRadius, hairColorPaint);
            }
        }
    }

    /**
     * sets up points that the drawAfro method will use to draw circles
     */
    private void setAfroBallCoords() {
        Random rand = new Random();
        float x, y;

        for (int i = 0; i < afroPoints.length; i++) {
            //making random coordinates in a box of space just above the face's eyes
            x = (float) rand.nextInt((int) (faceWidth - (2.5f * faceMargin))) + (headCenter[0] - (faceWidth / 2f) + (1.25f * faceMargin));
            y = (float) rand.nextInt((int) ((headCenter[1] - (faceHeight / 4f)) - (3f * faceMargin))) + (3f * faceMargin);

            afroPoints[i][0] = x;
            afroPoints[i][1] = y;
        }
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

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }
}
