package com.nyongconnect.android.virtuallab.fragment;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class PracticalThread extends Thread {
    PracticalView view;
    private boolean running = false;
    public  PracticalThread(PracticalView view){
        this.view=view;

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
            }
            finally {
                if(c != null){
                    view.getHolder().unlockCanvasAndPost(c);
                }

            }


        }
    }
}
