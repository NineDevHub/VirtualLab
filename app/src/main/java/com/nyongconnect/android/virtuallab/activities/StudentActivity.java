package com.nyongconnect.android.virtuallab.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.CanvasActivity;
import com.nyongconnect.android.virtuallab.PendulumActivity;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.fragment.FragmentReflectionLaw;
import com.nyongconnect.android.virtuallab.fragment.HomePracticeFragment;
import com.nyongconnect.android.virtuallab.fragment.ProfileFragment;
import com.nyongconnect.android.virtuallab.fragment.ReflectionActivity;
import com.nyongconnect.android.virtuallab.fragment.ReflectionFragment;
import com.nyongconnect.android.virtuallab.fragment.SimplePageAdapter;

public class StudentActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public int pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        pos = getIntent().getIntExtra("index",0);
        Toast.makeText(this,String.valueOf(pos),Toast.LENGTH_LONG).show();




        final FragmentReflectionLaw reflectiveLawPractical = new FragmentReflectionLaw();
        final HomePracticeFragment homePracticeFragment = new HomePracticeFragment();
        bottomNavigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){

                    case R.id.action_personal_studies:
                        transaction.replace(R.id.frame_layout, homePracticeFragment );
                        transaction.commit();
                        return true;
                    case R.id.action_practical:
//
                        if (pos == 0){
                            Intent intent = new Intent(StudentActivity.this, ReflectionActivity.class);
                            startActivity(intent);
                        }
                        else if (pos == 2){
                            Intent intent = new Intent(StudentActivity.this, PendulumActivity.class);
                            startActivity(intent);
                        }

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
                .replace(R.id.v_pager,fragment)
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
                break;
            case R.id.action_practical:
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
