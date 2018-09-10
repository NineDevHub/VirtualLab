package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.PendulumActivity;
import com.nyongconnect.android.virtuallab.adapters.PendulumResult;

import java.util.ArrayList;
import java.util.List;

public class PracticalView extends SurfaceView {
    PracticalThread practicalThread;
    public int width;
    Runnable runnable;
    Handler handler;
    int numberOscillated = 0;
    int numberOfOscillation = 0;
    int threadLength;
    public SurfaceHolder holder;
    Paint myPaint;
    float wih, x = 45;
    boolean isReturn = false;
    float high;
    List results = new ArrayList();


    public PracticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        myPaint = new Paint();
        myPaint.setColor(Color.WHITE);
        practicalThread = new PracticalThread(this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                boolean retry = true;
                practicalThread.setRunning(false);
                while (retry){
                    try {
                        practicalThread.join();
                        retry=false;
                    } catch (InterruptedException e) {
                        practicalThread.stop();
                    }
                    retry = false;
                }

            }
        });
    }



    public void startPractical(int numberOfOscillation, int lengthOfThread, Runnable runnable, Handler handler){
        if(!practicalThread.isAlive()){

            this.handler = handler;
            this.numberOfOscillation = numberOfOscillation;
            this.runnable = runnable;
            this.threadLength = lengthOfThread;
            practicalThread.setRunning(true);
            practicalThread.start();
        }

    }
    public void stopPractical(){
        handler.removeCallbacks(runnable);
        numberOscillated = 0;
        numberOfOscillation = 0;
        practicalThread.setRunning(false);
        practicalThread.interrupt();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // Toast.makeText(getContext(), String.valueOf(numberOfOscillation), Toast.LENGTH_SHORT).show();
        if(x == 45) {
            isReturn=false;
            numberOscillated++;
        }
        else if(x ==135){
            isReturn = true;
        }

        if ((threadLength >0)||(threadLength<=10)){
            if ((isReturn == true) &&(numberOscillated <= numberOfOscillation)){
                x-=5;

            }
            else if ((isReturn == false) &&(numberOscillated <= numberOfOscillation)){
                x+=5;
            }
            else {
                stopPractical();
                x =90;

            }

        }
        else  if ((threadLength >10)||(threadLength<=20)){
            if ((isReturn == true) &&(numberOscillated <= numberOfOscillation)){
                x-=3;

            }else if ((isReturn == false) &&(numberOscillated <= numberOfOscillation)){
                x+=3;
            }
            else {
                stopPractical();
                x =90;

            }
        }
        else {
            if ((isReturn == true) &&(numberOscillated <= numberOfOscillation)){
                x-=1.5;

            }else if ((isReturn == false) &&(numberOscillated <= numberOfOscillation)){
                x+=1.5;
            }
            else {
                stopPractical();
                x =90;

            }

        }



        wih = (float) (200 * Math.cos(x * (3.142/180)));
        high = (float) (200 *Math.sin(x * (3.142/180)));

        canvas.drawColor(Color.BLACK);
        canvas.drawLine(getWidth()/2, (getHeight()/4),(getWidth()/2) + wih, (getHeight()/4)+high,myPaint);
        canvas.drawCircle((getWidth()/2) + wih, (getHeight()/4) +high, 30,myPaint);
        canvas.drawText(numberOfOscillation+" "+getHeight(),50,50,myPaint);
//
//        canvas.drawLine(getWidth()/2, (getHeight()/2)-50, (float) ((getWidth()/2)+ 141.42) , (float) ((getHeight()/2)+141.14),myPaint);
//        canvas.drawCircle(getWidth()/2, (getHeight()/2) +180, 30,myPaint);
//        canvas.drawText(getWidth()+" "+getHeight(),50,50,myPaint);
//
//        canvas.drawLine(getWidth()/2, (getHeight()/2)-50, (float) ((getWidth()/2)+ 199.24), (float) ((getHeight()/2)+17.43),myPaint);
//        canvas.drawCircle(getWidth()/2, (getHeight()/2) +180, 30,myPaint);
//        canvas.drawText(getWidth()+" "+getHeight(),50,50,myPaint);
//
//        canvas.drawLine(getWidth()/2, (getHeight()/2)-50, (float) ((getWidth()/2) +196.96), (float) ((getHeight()/2)+34.73),myPaint);
//        canvas.drawCircle(getWidth()/2, (getHeight()/2) +180, 30,myPaint);
//        canvas.drawText(getWidth()+" "+getHeight(),50,50,myPaint);
//
//        canvas.drawLine(getWidth()/2, (getHeight()/2)-50, (float) ((getWidth()/2) +193.19), (float) ((getHeight()/2)+51.76),myPaint);
//        canvas.drawCircle(getWidth()/2, (getHeight()/2) +180, 30,myPaint);
//        canvas.drawText(getWidth()+" "+getHeight(),50,50,myPaint);
    }

}
