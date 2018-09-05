package com.nyongconnect.android.virtuallab.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nyongconnect.android.virtuallab.R;

public class ReflectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ReflectionLawView(this));
    }
}
