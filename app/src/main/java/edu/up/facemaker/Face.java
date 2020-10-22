package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
    private static final float afroRadius = 30.0f;

    public Face(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);

        randomizeColors();

        skinColorPaint.setColor(skinColor);
        eyeColorPaint.setColor(eyeColor);
        hairColorPaint.setColor(hairColor);
        hairColorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //hairColorPaint.setStrokeWidth(5.0f);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setStrokeWidth(4.0f);
        whitePaint.setColor(Color.WHITE);
    }

    /**
     * Sets skin, eye, and hair color to random color values
     */
    public void randomizeColors() {
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
    }

    /**
     * Draws a face.
     *
     * @param canvas    the canvas to draw on
     */
    @Override
    public void onDraw(Canvas canvas) {
        float[] headCenter = {faceWidth / 2f + faceMargin, faceHeight / 2f + 5.0f * faceMargin};
        //drawing head
        canvas.drawOval(headCenter[0] - faceWidth / 2f, headCenter[1] + faceHeight / 2f, headCenter[0] + faceWidth / 2f, headCenter[1] - faceHeight / 2f, skinColorPaint);
        //drawing nose
        canvas.drawLine(headCenter[0], headCenter[1] -  faceMargin, headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, blackPaint);
        canvas.drawLine(headCenter[0] + faceMargin / 1.5f, headCenter[1] + faceMargin, headCenter[0], headCenter[1] + faceMargin, blackPaint);
        //drawing mouth
        canvas.drawLine(headCenter[0] - faceWidth / 4f, headCenter[1] + 4f * faceMargin, headCenter[0] + faceWidth / 4f, headCenter[1] + 4f * faceMargin, blackPaint);

        drawEyes(canvas, headCenter);

        //drawSpikesHair(canvas, headCenter);
        //drawBowlCutHair(canvas, headCenter);
        drawAfro(canvas, headCenter);
    }

    /**
     * Helper method for onDraw. Draws the eyes of the face.
     *
     * @param canvas        the canvas to draw on
     * @param headCenter    coordinates so that the eyes can be drawn relative to the center of the face
     */
    private void drawEyes(Canvas canvas, float[] headCenter) {
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
     * @param canvas        the canvas to draw on
     * @param headCenter    coordinates so that the eyes can be drawn relative to the center of the face
     */
    private void drawBowlCutHair(Canvas canvas, float[] headCenter) {
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
     * @param canvas        the canvas to draw on
     * @param headCenter    coordinates so that the eyes can be drawn relative to the center of the face
     */
    private void drawSpikesHair(Canvas canvas, float[] headCenter) {
        float hairWidth = faceWidth - (2.5f * faceMargin);
        float hairHeight = 8.5f * faceMargin;

        //coordinates for where to start the path
        float x = headCenter[0] - (faceWidth / 2f) + (1.25f * faceMargin);
        float y = headCenter[1] - (faceHeight / 4f);

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
        hair.lineTo(x + hairWidth * 1f / 10f, y - hairHeight);
        hair.lineTo(x + hairWidth * 2f / 10f, y - hairHeight + 4f * faceMargin);
        hair.lineTo(x + hairWidth * 3f / 10f, y - hairHeight);
        hair.lineTo(x + hairWidth * 4f / 10f, y - hairHeight + 4f * faceMargin);
        hair.lineTo(x + hairWidth * 5f / 10f, y - hairHeight);
        hair.lineTo(x + hairWidth * 6f / 10f, y - hairHeight + 4f * faceMargin);
        hair.lineTo(x + hairWidth * 7f / 10f, y - hairHeight);
        hair.lineTo(x + hairWidth * 8f / 10f, y - hairHeight + 4f * faceMargin);
        hair.lineTo(x + hairWidth * 9f / 10f, y - hairHeight);
        hair.lineTo(x + hairWidth * 10f / 10f, y);
        hair.close();

        canvas.drawPath(hair, hairColorPaint);;
    }

    private void drawAfro(Canvas canvas, float[] headCenter) {
        Random rand = new Random();
        float x, y;

        for (float[] point : getAfroBallCoords(headCenter)) {
            if (point[0] != 0 && point[1] != 0) {
                canvas.drawCircle(point[0], point[1], afroRadius, hairColorPaint);
            }
        }
    }

    private float[][] getAfroBallCoords(float[] headCenter) {
        Random rand = new Random();
        float[][] points = new float[500][2];
        float x, y, a, b;

        float[] origin = {headCenter[0], headCenter[1] - (faceHeight / 3f)};

        for (int i = 0; i < points.length; i++) {
            x = (float) rand.nextInt((int) (faceWidth - (2.5f * faceMargin))) + (headCenter[0] - (faceWidth / 2f) + (1.25f * faceMargin));
            y = (float) rand.nextInt((int) ((headCenter[1] - (faceHeight / 4f)) - (5f * faceMargin))) + (4.5f * faceMargin);
            a = x - origin[0];
            b = y - origin[1];

            if (Math.pow(a, 2) + Math.pow(b, 2) <= Math.pow(8f * faceMargin, 2)) {
                points[i][0] = x;
                points[i][1] = y;
            }
        }
        return points;
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
