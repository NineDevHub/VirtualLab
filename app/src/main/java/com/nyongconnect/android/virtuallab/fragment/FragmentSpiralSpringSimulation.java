package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.nyongconnect.android.virtuallab.CommunicatorOhmsLaw;
import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.canvas.CanvasOhmsLaw;
import com.nyongconnect.android.virtuallab.canvas.CanvasSpiralSpring;

import java.util.ArrayList;

public class FragmentSpiralSpringSimulation extends Fragment {



    private View view;
    private View img;
    private CanvasSpiralSpring mSpringView;


    private float mDampingRatio;
    private float mStiffness;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_spiral_spring_practical,container,false);



        mSpringView = view.findViewById(R.id.actual_spring);
        img = view.findViewById(R.id.imageView);

        final SpringAnimation anim = new SpringAnimation(img, DynamicAnimation.TRANSLATION_Y,
                0 /* final position */);
        anim.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float v, float v1) {
                // Update the drawing of the spring.
                mSpringView.setMassHeight(img.getY());
            }
        });

        ((View) img.getParent()).setOnTouchListener(new View.OnTouchListener() {
            public float touchOffset;
            public VelocityTracker vt;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    // check whether the touch happens inside of the img view.
                    boolean inside = motionEvent.getX() >= img.getX()
                            && motionEvent.getX() <= img.getX() + img.getWidth()
                            && motionEvent.getY() >= img.getY()
                            && motionEvent.getY() <= img.getY() + img.getHeight();

                    anim.cancel();

                    if (!inside) {
                        return false;
                    }
                    // Apply this offset to all the subsequent events
                    touchOffset = img.getTranslationY() - motionEvent.getY();
                    vt = VelocityTracker.obtain();
                    vt.clear();
                }

                vt.addMovement(motionEvent);

                if (motionEvent.getActionMasked() == MotionEvent.ACTION_MOVE) {
                    img.setTranslationY(motionEvent.getY() + touchOffset);
                    // Updates the drawing of the spring.
                    mSpringView.setMassHeight(img.getY());
                } else if (motionEvent.getActionMasked() == MotionEvent.ACTION_CANCEL
                        || motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                    // Compute the velocity in unit: pixel/second
                    vt.computeCurrentVelocity(1000);
                    float velocity = vt.getYVelocity();
                    anim.getSpring().setDampingRatio(0).setStiffness(100);
                    anim.setStartVelocity(velocity).start();
                    vt.recycle();
                }
                return true;
            }
        });


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        final View viewWeight = view.findViewById(R.id.imageView_weight);
//
//
//
//        SpringAnimation springAnim = new SpringAnimation(viewWeight, DynamicAnimation.TRANSLATION_Y);
//
//        SpringForce springForce = new SpringForce();
//        springForce.setFinalPosition(200f);
//        springForce.setStiffness(100);
//        springForce.setDampingRatio(0);
//        springAnim.setSpring(springForce);
//        springAnim.start();


//        Log.i("Spring Value "," "+springForce.getStiffness());



    }



}
