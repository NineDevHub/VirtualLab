package com.nyongconnect.android.virtuallab.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nyongconnect.android.virtuallab.CommunicatorOhmsLaw;
import com.nyongconnect.android.virtuallab.CommunicatorSimplePendulum;
import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.Pendulum;
import com.nyongconnect.android.virtuallab.Physics;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.adapters.LabWorkPagerAdapter;
import com.nyongconnect.android.virtuallab.fragment.FragmentOhmsLawResult;
import com.nyongconnect.android.virtuallab.fragment.FragmentSimplePendulumResult;

public class LabWorkActivity extends AppCompatActivity implements CommunicatorOhmsLaw,CommunicatorSimplePendulum{
    BottomNavigationView bottomNavigationView;


    TabLayout tabLayout;
    ViewPager viewPager;
    LabWorkPagerAdapter viewPagerAdapter;

    int[] tabIcons = {R.drawable.ic_atomic,R.drawable.ic_lab_result};


    public  static String code;
    public  static boolean isInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_work);


        Intent intent =  getIntent();

        Physics physics = (Physics) intent.getSerializableExtra("physics");

        code = physics.getCode();

        isInit = physics.isInitialized();

        Log.i("Code :",code);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_personal_studies:

                        Intent intent1 = new Intent(LabWorkActivity.this, SelfStudyActivity.class);
                        intent1.putExtra("code",code);
                        startActivity(intent1);
                        return true;
                    case R.id.action_practical:
                        return true;


                }
                return false;
            }
        });



        viewPager = findViewById(R.id.vpPager);
        viewPagerAdapter = new LabWorkPagerAdapter(getSupportFragmentManager(),isInit);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.view_pager_tab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }


    @Override
    protected void onResume() {
        super.onResume();

        bottomNavigationView.setSelectedItemId(R.id.action_practical);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupTabIcons() {

        for (int i = 0;i<tabIcons.length;i++) {
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem submittedWork = menu.findItem(R.id.menu_sumitted_work);
        submittedWork.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void result(OhmsLawResult result) {
//        int position = viewPager.getCurrentItem();

//
//        String fragmentTag = getFragmentTag(viewPager.getId(), 2);
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);


        FragmentOhmsLawResult.updateTableInfo(result);
    }

    private static String getFragmentTag(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    @Override
    public void result(Pendulum result) {

        FragmentSimplePendulumResult.updateTableInfo(result);

    }
}
