package com.nyongconnect.android.virtuallab.fragment;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

class ReflectionThread extends Thread{
    private ReflectionLawView view;
    private boolean running = false;


    public ReflectionThread(ReflectionLawView view){
        this.view = view;

    }

    public void setRunning(boolean run){
        running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (running){
            Canvas c = null;
            try{
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(c);
                }
            }catch (Exception e){
                setRunning(false);
            }
            finally {
                if(c != null){
                    view.getHolder().unlockCanvasAndPost(c);
                }

            }


        }
    }
}
