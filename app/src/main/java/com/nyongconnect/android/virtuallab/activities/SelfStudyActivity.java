package com.nyongconnect.android.virtuallab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.fragment.FragmentOhmsLawSelfStudy;
import com.nyongconnect.android.virtuallab.fragment.FragmentSimplePendulumSelfStudy;

public class SelfStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_study);

        String code = getIntent().getStringExtra("code");


        if (code.equals("001")) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_self_study, new FragmentOhmsLawSelfStudy())
                    .commit();


        } else {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_self_study,new FragmentSimplePendulumSelfStudy())
                    .commit();

        }



    }
}
