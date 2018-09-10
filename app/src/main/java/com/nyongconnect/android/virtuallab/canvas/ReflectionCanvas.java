package com.nyongconnect.android.virtuallab.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by enyason on 9/4/18.
 */

public class ReflectionCanvas extends View {

    private Bitmap bitmap;
    private int x = 0;
    float incidence = 50;
    float refractedRender = 0;
    float incidenceRender =0;
    boolean end = false;
    Paint paint;
    Paint paintWhite;
    Paint paintRed;
    Paint paintBlack;





    public ReflectionCanvas(Context context) {
        super(context);

//        setBackgroundColor(Color.WHITE);
//
        init(context);

    }


    private void init(Context  context) {

//        bitmapResistor = getBitmapFromDrawable(context,R.drawable.ic_resistor);
//        bitmapVoltage = getBitmapFromDrawable(context,R.drawable.ic_voltmeter);
//        bitmapAmmeter = getBitmapFromDrawable(context,R.drawable.ic_ammeter);

//        wirePaintBrush.setColor(Color.RED);
//        wirePaintBrush.setStyle(Paint.Style.FILL);
//
//        int halfHeightAmmeter = bitmapAmmeter.getHeight()/2;
//        int halfWidthVoltmeter = bitmapAmmeter.getWidth()/2;



        paint = new Paint();
        paintWhite = new Paint();
        paintRed = new Paint();
        paintBlack = new Paint();
        paintBlack.setTextSize(100);
        paintRed.setStrokeWidth(5);
        paint.setStrokeWidth(5);
        paintRed.setColor(Color.RED);
        paintWhite.setColor(Color.WHITE);
        paint.setColor(Color.BLACK);

//        path = new Path();
//        path.moveTo(100, 100+halfHeightAmmeter);
//        path.lineTo(200, 100+halfHeightAmmeter);
//        path.moveTo(200+bitmapAmmeter.getWidth(),100+halfHeightAmmeter);
//        path.lineTo(400+bitmapResistor.getWidth()/2,100+halfHeightAmmeter);
//        path.moveTo(400+bitmapResistor.getWidth()/2,100+halfHeightAmmeter);
//        path.lineTo(400+bitmapResistor.getWidth()/2,250);
//        path.moveTo(400+bitmapResistor.getWidth()/2,250+bitmapResistor.getHeight());
//        path.lineTo(500,350);
//        path.lineTo(200, 500);
//        path.lineTo(200, 300);
//        path.lineTo(350, 300);


    }

    public ReflectionCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReflectionCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setText(float num){
        incidence = num;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        incidenceRender = (float) (520 - (incidence * 5.77777777778));
        refractedRender = (float) (520 + (incidence * 5.77777777778));


        canvas.drawCircle(0, 520, 470, paint);
        canvas.drawCircle(0, 520, 450, paintWhite);
        canvas.drawText("i = "+incidence,300, 100, paintBlack);
        canvas.drawText("r = "+incidence,300, 1000, paintBlack);
        canvas.drawLine(0, 520, 470, 520, paint);
        canvas.drawLine(0, 520, incidenceRender, incidenceRender, paintRed);
        canvas.drawLine(0, 520, incidenceRender, refractedRender, paintRed);

//        canvas.drawBitmap(bitmapResistor, 400, 250, null);
//        canvas.drawBitmap(bitmapAmmeter,200,100,null);
//        canvas.drawBitmap(bitmapVoltage,100,250,null);

//        canvas.drawLine(100,100,200,100,wirePaintBrush);

//        wirePath.moveTo(400,400);
//        wirePath.lineTo(600,600);
//        wirePath.moveTo(600,600);
//        wirePath.lineTo(200,600);
//        wirePath.moveTo(200,600);
//        wirePath.lineTo(400,400);
//        canvas.drawPath(wirePath,wirePaintBrush);





    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
//        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
//
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        } else if (drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable) {
//            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//            drawable.draw(canvas);
//
//            return bitmap;
//        } else {
//            throw new IllegalArgumentException("unsupported drawable type");
//        }
//    }


}
