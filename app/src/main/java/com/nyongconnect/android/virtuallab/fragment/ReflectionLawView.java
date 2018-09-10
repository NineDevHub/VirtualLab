package com.nyongconnect.android.virtuallab.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nyongconnect.android.virtuallab.R;

public class ReflectionLawView extends SurfaceView{
    ReflectionActivity activity = new ReflectionActivity();
    private Bitmap bitmap;
    private SurfaceHolder holder;
    private ReflectionThread gameThread;
    private int x = 0;
    float incidence = 80;
    float radius = 0;
    float refractedXRender = 0;
    float refractedYRender = 0;
    float incidenceXRender =0;
    float incidenceYRender =0;
    boolean end = false;
    Paint paint;
    Paint paintWhite;
    Paint paintRed;
    Paint paintBlack;
    private float bigRadius = getWidth() - 100;
    private float smallRadius = bigRadius - 10;

    public ReflectionLawView(Context context, AttributeSet attrs) {
        super(context, attrs);


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

        gameThread = new ReflectionThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (!gameThread.isAlive()){
                gameThread.setRunning(true);
                gameThread.start();}
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry){
                    try {
                        gameThread.join();
                    } catch (InterruptedException e) {
                        gameThread.stop();
                    }
                    retry = false;
                }

            }
        });
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }
    public void stopPractical(){
        gameThread.setRunning(false);
        gameThread.interrupt();
    }

    @NonNull
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);



        incidenceXRender = 0 + (float) (450 * Math.cos(incidence * (3.142/180)));
        incidenceYRender = 520 - (float) (450 *Math.sin(incidence * (3.142/180)));
        refractedXRender = 0 + (float) (450 * Math.cos(incidence  * (3.142/180)));
        refractedYRender = 520 + (float) (450 *Math.sin(incidence * (3.142/180)));

        canvas.drawCircle(0, 520, 470, paint);
        canvas.drawCircle(0, 520, 450, paintWhite);
        canvas.drawText("i = "+incidence,300, 100, paintBlack);
        canvas.drawText("r = "+incidence,300, 1000, paintBlack);
        canvas.drawLine(0, 520, 470, 520, paint);
        canvas.drawLine(0, 520, incidenceXRender, incidenceYRender, paintRed);
        canvas.drawLine(0, 520, refractedXRender, refractedYRender, paintRed);
//        canvas.drawCircle(1, getHeight()/3, bigRadius, paint);
//        canvas.drawCircle(1, getHeight()/3, smallRadius, paintWhite);
//        canvas.drawLine(1, getHeight()/3, bigRadius, getHeight()/3, paint);



    }

}
