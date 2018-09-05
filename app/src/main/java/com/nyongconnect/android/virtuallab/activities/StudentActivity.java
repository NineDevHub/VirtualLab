package com.nyongconnect.android.virtuallab.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.fragment.ProfileFragment;
import com.nyongconnect.android.virtuallab.fragment.ReflectionFragment;

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
//                switch (item.getItemId()){
//                    case R.id.action_profile:
//                        ProfileFragment profileFragment = new ProfileFragment();
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frame_layout, profileFragment);
//                        transaction.commit();
//                        return true;
//                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        ProfileFragment profileFragment = new ProfileFragment();
        ReflectionFragment reflectionFragment = new ReflectionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (itemId){
            case R.id.menu_profile:
                transaction.replace(R.id.frame_layout, reflectionFragment);
                break;
            case R.id.action_simulation:
                transaction.replace(R.id.frame_layout, reflectionFragment );
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
