package com.nyongconnect.android.virtuallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nyongconnect.android.virtuallab.canvas.MyCanvas;

public class CanvasActivity extends AppCompatActivity {

    MyCanvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

//        myCanvas = new MyCanvas(this);

//        setContentView(myCanvas);


    }
}
