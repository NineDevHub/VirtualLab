package com.nyongconnect.android.virtuallab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class OhmsLawStudyActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohms_law_study);

        toolbar = findViewById(R.id.toolbar_ohms_law);

        setSupportActionBar(toolbar);
    }
}
