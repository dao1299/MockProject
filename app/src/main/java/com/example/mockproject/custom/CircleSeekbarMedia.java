package com.example.mockproject.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleSeekbarMedia extends View {
    final String TAG = "CustomCircle";
    private final float widthProgress = 30;
    private final float distanceToRect = 20;
    private float xTam;
    private float yTam;
    private float radius;
    private float widthLine = 20;
    private boolean isInButtonWake = false;
    private boolean isInButtonSleep = false;

    private float degreeSleep = 0;
    private float degreeWake = 2;


    private Paint paintBackground;
    private Paint paintPoint1;
    private Paint paintPoint2;
    private Paint paintProgress;
    private Paint paintText;
    private Paint paintLineClock;

    public CircleSeekbarMedia(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        paintBackground = new Paint();
        paintBackground.setColor(0x99999999);
        paintBackground.setStrokeWidth(widthProgress);
        paintBackground.setStyle(Paint.Style.STROKE);

        paintPoint1 = new Paint();
        paintPoint1.setColor(Color.GREEN);
        paintPoint1.setStrokeWidth((widthProgress + 10) / 2);
        paintPoint1.setStyle(Paint.Style.FILL);

        paintPoint2 = new Paint();
        paintPoint2.setColor(0xFFD9519D);
        paintPoint2.setStrokeWidth((widthProgress + 10) / 2);
        paintPoint2.setStyle(Paint.Style.FILL);

//        ED8770
        paintProgress = new Paint();
        paintProgress.setColor(0xFFED8770);
        paintProgress.setStrokeWidth(widthProgress + 10);
        paintProgress.setStyle(Paint.Style.STROKE);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setStrokeWidth(widthProgress);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(40);
        paintText.setTextAlign(Paint.Align.CENTER);

        paintLineClock = new Paint();
        paintLineClock.setColor(Color.BLACK);
        paintLineClock.setStyle(Paint.Style.FILL);
        paintLineClock.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(xTam, yTam, radius, paintBackground);
        canvas.save();
        canvas.rotate(-90, xTam, yTam);
        @SuppressLint("DrawAllocation") RectF rect2 = new RectF(xTam - radius,
                yTam - radius,
                xTam + radius,
                yTam + radius);
        Log.i(TAG, "onDraw: d2=" + degreeWake);
        float sweep = (degreeWake > degreeSleep) ? (degreeWake - degreeSleep) : (360 - (degreeSleep - degreeWake));
        canvas.drawArc(rect2, degreeSleep, sweep, false, paintProgress);
        canvas.rotate(degreeSleep, xTam, yTam);
        canvas.restore();

//        canvas.drawCircle((float) (radius * Math.sin(Math.toRadians(degreeSleep))) + xTam, yTam + (float) -(radius * Math.cos(Math.toRadians(degreeSleep))), widthProgress / 2f, paintPoint1);


        canvas.drawCircle((float) (radius * Math.sin(Math.toRadians(degreeWake))) + xTam, yTam + (float) -(radius * Math.cos(Math.toRadians(degreeWake))), widthProgress / 1.2f, paintPoint2);

        canvas.save();
    }


    private boolean isButtonSleep(float x, float y) {
        float x1 = (float) (xTam + radius * Math.sin(Math.toRadians(degreeSleep)));
        float y1 = (float) (yTam - radius * Math.cos(Math.toRadians(degreeSleep)));
        Log.d(TAG, "Button2: button:(" + x1 + "," + y1 + ") ; click:(" + x + "," + y + ");");
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1)) < widthProgress;
    }

    private boolean isButtonWake(float x, float y) {
        float x1 = (float) (xTam + radius * Math.sin(Math.toRadians(degreeWake)));
        float y1 = (float) (yTam - radius * Math.cos(Math.toRadians(degreeWake)));
        Log.d(TAG, "eButton1: button:(" + x1 + "," + y1 + ") ; click:(" + x + "," + y + ");");
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1)) < widthProgress;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        xTam = width / 2f;
        yTam = height / 2f;
        Log.i(TAG, "onMeasure: " + width + " " + height);
        if (width > height) {
            radius = height / 2.5f;
        } else {
            radius = width / 2.5f;
        }
        setMeasuredDimension(width, height);

    }

    private float XYtoDegree(float touchX, float touchY) {
        float x = xTam - touchX;
        float y = touchY - yTam;
        double v = Math.toDegrees(Math.atan(x / y));
        if (touchX > xTam && touchY > yTam) v += 180;
        else if (touchX < xTam && touchY > yTam) v += 180;
        else if (touchX < xTam && touchY < yTam)
            v += 360;
        Log.i(TAG, "XYtoDegree: " + v);
        return (float) v;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if (isButtonWake(x, y)) {
                    isInButtonWake = true;
                    degreeWake = XYtoDegree(event.getX(), event.getY());
                    Log.i(TAG, "onTouchEvent: in button hour: " + degreeWake * 144 / 360);
                } else if (isButtonSleep(x, y)) {
                    isInButtonSleep = true;
                    degreeSleep = XYtoDegree(event.getX(), event.getY());
                    Log.i(TAG, "onTouchEvent: in button hour: " + degreeSleep * 144 / 360);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isInButtonWake)
                    degreeWake = XYtoDegree(event.getX(), event.getY());
                else if (isInButtonSleep)
                    degreeSleep = XYtoDegree(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();
        return true;
    }
}
