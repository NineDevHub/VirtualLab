package com.nyongconnect.android.virtuallab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.adapters.CourseAdapter;
import com.nyongconnect.android.virtuallab.adapters.ExperimentListingAdapter;

public class AvailableExperimentActivity extends AppCompatActivity implements ExperimentListingAdapter.ExperimentListingClickListener{
    RecyclerView recyclerView;
    ExperimentListingAdapter experimentListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_experiment);
        recyclerView = findViewById(R.id.rv_experiment_listing);
        experimentListingAdapter = new ExperimentListingAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(experimentListingAdapter);
    }

    @Override
    public void onExperimentListingClickListener(int clickedIndex) {
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}
