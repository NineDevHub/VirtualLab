package com.nyongconnect.android.virtuallab.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.adapters.CourseAdapter;

public class HomeActivity extends AppCompatActivity implements CourseAdapter.ListItemClickListener{

    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.rv_display_offered_courses);
        courseAdapter = new CourseAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(courseAdapter);

    }

    @Override
    public void onListItemClickListener(int clickedIndex) {
        Intent intent = new Intent(this, AvailableExperimentActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
