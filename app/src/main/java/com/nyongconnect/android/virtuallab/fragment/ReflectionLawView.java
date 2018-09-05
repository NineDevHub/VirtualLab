package com.nyongconnect.android.virtuallab.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nyongconnect.android.virtuallab.R;

public class ReflectionLawView extends SurfaceView {
    private Bitmap bitmap;
    private SurfaceHolder holder;
    private ReflectionThread gameThread;
    private int x = 0;
    float incidence = 80;
    float refractedRender = 0;
    float incidenceRender =0;
    boolean end = false;
    Paint paint;
    Paint paintWhite;
    Paint paintRed;
    Paint paintBlack;

    public ReflectionLawView(Context context) {
        super(context);
        gameThread = new ReflectionThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameThread.setRunning(true);
                gameThread.start();
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
                        e.printStackTrace();
                    }
                    retry = false;
                }

            }
        });
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @NonNull
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        incidenceRender = (float) (520 - (incidence * 5.77777777778));
        refractedRender = (float) (520 + (incidence * 5.77777777778));

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
        canvas.drawCircle(0, 520, 470, paint);
        canvas.drawCircle(0, 520, 450, paintWhite);
        canvas.drawText("i = "+incidence,300, 100, paintBlack);
        canvas.drawText("r = "+incidence,300, 1000, paintBlack);
        canvas.drawLine(0, 520, 470, 520, paint);
        canvas.drawLine(0, 520, incidenceRender, incidenceRender, paintRed);
        canvas.drawLine(0, 520, incidenceRender, refractedRender, paintRed);


    }

}
