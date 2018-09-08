package com.nyongconnect.android.virtuallab.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.nyongconnect.android.virtuallab.CanvasActivity;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.fragment.HomePracticeFragment;
import com.nyongconnect.android.virtuallab.fragment.ProfileFragment;
import com.nyongconnect.android.virtuallab.fragment.ReflectionActivity;
import com.nyongconnect.android.virtuallab.fragment.ReflectionFragment;
import com.nyongconnect.android.virtuallab.fragment.practicalFragment;

public class StudentActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){
                    case R.id.action_simulation:
//                        ReflectionFragment refFragment = new ReflectionFragment();
//                        transaction.replace(R.id.frame_layout, refFragment);
//                        transaction.commit();

                        int pos = getIntent().getIntExtra("index",0);
                        if (pos == 0) {
                            startActivity(new Intent(StudentActivity.this, ReflectionActivity.class));



                        } else {
                            startActivity(new Intent(StudentActivity.this, CanvasActivity.class));


                        }
                        return true;
                    case R.id.action_personal_studies:
                        loadFragment(new HomePracticeFragment());
                        return true;
                    case R.id.action_practical:
                        loadFragment(new practicalFragment());
                        return true;


                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void loadFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_container,fragment)
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        ProfileFragment profileFragment = new ProfileFragment();
        ReflectionFragment reflectionFragment = new ReflectionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (itemId){
            case R.id.menu_profile:
                transaction.replace(R.id.frame_layout_container, profileFragment);
                break;
            case R.id.action_simulation:
                int pos = getIntent().getIntExtra("index",0);
                if (pos == 0) {
                    startActivity(new Intent(StudentActivity.this, ReflectionActivity.class));



                } else {
                    startActivity(new Intent(StudentActivity.this, CanvasActivity.class));


                }
//                transaction.replace(R.id.frame_layout, reflectionFragment );
                break;

        }

        transaction.commit();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem submittedWork = menu.findItem(R.id.menu_sumitted_work);
        submittedWork.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }
}
