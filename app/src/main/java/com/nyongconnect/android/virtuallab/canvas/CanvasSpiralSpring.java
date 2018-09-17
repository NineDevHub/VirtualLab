package com.nyongconnect.android.virtuallab.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by enyason on 9/9/18.
 */

public class CanvasSpiralSpring extends View {


    Paint paintCircle, paintLine;
    Path pathLines;


    private float mLastHeight = 175;


    public CanvasSpiralSpring(Context context) {
        super(context);


        init(context);

    }

    public void setMassHeight(float height) {
        mLastHeight = height;
        invalidate();
    }


    private void init(Context context) {

        setWillNotDraw(false);

        paintCircle = new Paint();
        paintCircle.setColor(Color.RED);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(5);

        paintLine = new Paint();

        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(10);
        paintLine.setColor(Color.GRAY);


//        pathLines = new Path();
//
////
//        pathLines.moveTo(400,410);
//        pathLines.lineTo(400,430);
////
//        pathLines.moveTo(420,430);
//        pathLines.lineTo(380,430);
////
//        pathLines.moveTo(380,430);
//        pathLines.lineTo(420,440);
//
//        pathLines.moveTo(420,440);
//        pathLines.lineTo(380,440);
//
//        pathLines.moveTo(380,440);
//        pathLines.lineTo(420,450);



    }

    public CanvasSpiralSpring(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CanvasSpiralSpring(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.drawCircle(400, 400, 10, paintCircle);
//        canvas.drawPath(pathLines, paintLine);
//        canvas.drawLine(400,410,400,420,paintLine);


        // Draws the spring
        // 30px long, 15 sections
        int num = 20;
        float sectionLen = 150; // px
        final float x = canvas.getWidth() / 2;
        float y = 0;
        float sectionHeight = mLastHeight / num;
        float sectionWidth = (float) Math.sqrt(sectionLen * sectionLen
                - sectionHeight * sectionHeight);
        canvas.drawLine(x, y, x + sectionWidth / 2, sectionHeight / 2, paintLine);
        float lastX = x + sectionWidth / 2;
        float lastY = sectionHeight / 2;
        for (int i = 1; i < num; i++) {
            canvas.drawLine(lastX, lastY, 2 * x - lastX, lastY + sectionHeight, paintLine);
            lastX = 2 * x - lastX;
            lastY = lastY + sectionHeight;
        }
        canvas.drawLine(lastX, lastY, x, mLastHeight, paintLine);

    }

    public void updateSlopePosition(float current, float voltage) {

    }

    public void invalidateCanvas(Context context, int drawable, float x, float y) {


    }


    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }


}


