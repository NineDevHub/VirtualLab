package com.nyongconnect.android.virtuallab.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.R;

public class ReflectionActivity extends AppCompatActivity {

Spinner spinner; ReflectionLawView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);



    }

    public void startSimulation(View view) {
        spinner = findViewById(R.id.spinner_list);
        this.view = findViewById(R.id.reflection_law);
        try {
            this.view.incidence = Float.parseFloat(spinner.getSelectedItem().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "select angle", Toast.LENGTH_LONG).show();
        }



    }
}
