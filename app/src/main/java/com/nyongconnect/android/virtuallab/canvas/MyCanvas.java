package com.nyongconnect.android.virtuallab.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;

import com.nyongconnect.android.virtuallab.R;

/**
 * Created by enyason on 9/4/18.
 */

public class MyCanvas extends View {
    ;
    Paint xyGraphPaint, slopePaint;
    public Bitmap bitmapVoltage, bitmapAmmeter, bitmapResistor;

    Paint paint;
    Path path, pathXYGraph, pathSlope;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCanvas(Context context) {
        super(context);

//        setBackgroundColor(Color.WHITE);
//
        init(context);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(Context context) {

        bitmapResistor = getBitmapFromDrawable(context, R.drawable.ic_resistor);
        bitmapVoltage = getBitmapFromDrawable(context, R.drawable.ic_voltmeter);
        bitmapAmmeter = getBitmapFromDrawable(context, R.drawable.ic_ammeter);


        int halfHeightAmmeter = bitmapAmmeter.getHeight() / 2;
        int halfWidthVoltmeter = bitmapAmmeter.getWidth() / 2;


        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        xyGraphPaint = new Paint();
        xyGraphPaint.setColor(Color.BLACK);
        xyGraphPaint.setStyle(Paint.Style.STROKE);
        xyGraphPaint.setStrokeWidth(8);


        slopePaint = new Paint();
        slopePaint.setColor(Color.RED);
        slopePaint.setStyle(Paint.Style.STROKE);
        slopePaint.setStrokeWidth(8);


        path = new Path();
        path.moveTo(100, 100 + halfHeightAmmeter);
        path.lineTo(200, 100 + halfHeightAmmeter);
        path.moveTo(200 + bitmapAmmeter.getWidth(), 100 + halfHeightAmmeter);
        path.lineTo(400 + bitmapResistor.getWidth() / 2, 100 + halfHeightAmmeter);
        path.moveTo(400 + bitmapResistor.getWidth() / 2, 100 + halfHeightAmmeter);
        path.lineTo(400 + bitmapResistor.getWidth() / 2, 250);
        path.moveTo(400 + bitmapResistor.getWidth() / 2, 250 + bitmapResistor.getHeight());
        path.lineTo(400 + bitmapResistor.getWidth() / 2, 400);

//        path.moveTo(400+bitmapResistor.getWidth()/2,400);
//        path.lineTo(100,400);
        path.moveTo(100, 100 + halfHeightAmmeter);
        path.lineTo(100, 250);
        path.moveTo(100, 250 + bitmapVoltage.getHeight());
        path.lineTo(100, 400);
        path.moveTo(100, 400);
        path.lineTo(380 + bitmapAmmeter.getWidth(), 400);

//


        pathXYGraph = new Path();
        pathXYGraph.moveTo(100, 600);
        pathXYGraph.lineTo(100, 1100);
        pathXYGraph.moveTo(100, 1100);
        pathXYGraph.lineTo(600, 1100);


        pathSlope = new Path();
        pathSlope.moveTo(100,1100);
        pathSlope.lineTo(600,1100);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(bitmapResistor, 400, 250, null);
        canvas.drawBitmap(bitmapAmmeter, 200, 100, null);
        canvas.drawBitmap(bitmapVoltage, 100 - bitmapVoltage.getWidth() / 2, 250, null);

//        canvas.drawLine(100,100,200,100,wirePaintBrush);

//        wirePath.moveTo(400,400);
//        wirePath.lineTo(600,600);
//        wirePath.moveTo(600,600);
//        wirePath.lineTo(200,600);
//        wirePath.moveTo(200,600);
//        wirePath.lineTo(400,400);
//        canvas.drawPath(wirePath,wirePaintBrush);

        canvas.drawPath(path, paint);
        canvas.drawPath(pathXYGraph, xyGraphPaint);
        canvas.drawPath(pathSlope, slopePaint);


    }

    public void updateSlopePosition(float current, float voltage) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void invalidateCanvas(Context context, int drawable, float x, float y) {

        if (drawable != 0) {
            bitmapAmmeter = getBitmapFromDrawable(context, drawable);

        }

        pathSlope.reset();

        pathSlope.moveTo(100,1100);
        pathSlope.lineTo(100, y);
        invalidate();

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
