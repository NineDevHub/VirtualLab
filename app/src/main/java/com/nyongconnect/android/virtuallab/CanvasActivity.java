package com.nyongconnect.android.virtuallab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.canvas.ReflectionCanvas;

public class CanvasActivity extends AppCompatActivity {

    ReflectionCanvas myCanvas;

    SeekBar seekBarVoltage, seekBarResistance;

    TextView tvResistance, tvVoltage,tvCurrent;

    float valCurrent = 0, valVoltage = 0, valResistance = 1000;

    private float xPointGraph=100, yPointGraph=1100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

//        myCanvas = new MyCanvas(this);

//        setContentView(myCanvas);


        seekBarVoltage = findViewById(R.id.seek_bar_voltage);
        seekBarResistance = findViewById(R.id.seek_bar_resistance);
        myCanvas = findViewById(R.id.canvas_ohms_law);

        tvResistance = findViewById(R.id.tv_resistance);
        tvVoltage = findViewById(R.id.tv_voltage);
        tvCurrent = findViewById(R.id.tv_current);


        seekBarVoltage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valVoltage = progress;

                float current = valVoltage/valResistance;

                tvVoltage.setText("V = " + progress + "v");
                tvCurrent.setText("I = "+current+"A");


                yPointGraph = 1100 - (50 * progress);
                xPointGraph = 100 + (500*current);

                int drawable;
                if (progress > 0) {
                    drawable = R.drawable.ic_ammeter_current;

                } else {
                    drawable = R.drawable.ic_ammeter;

                }


//                myCanvas.invalidateCanvas(CanvasActivity.this, drawable,xPointGraph,yPointGraph);

            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        seekBarResistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//                valResistance = progress;
//                float current = valVoltage / valResistance;
//
//                tvResistance.setText("Resistance = " + progress + getString(R.string.omega));
//                tvCurrent.setText("I = "+current+"A");
//
//
//                yPointGraph = 1100 - (50 * valVoltage);
//                xPointGraph = 100 + (50 * current);
//
////                myCanvas.invalidateCanvas(CanvasActivity.this, 0,xPointGraph,yPointGraph);
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


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
