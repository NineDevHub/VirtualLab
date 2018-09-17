package com.nyongconnect.android.virtuallab.adapters;

/**
 * Created by enyason on 9/8/18.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;
import com.nyongconnect.android.virtuallab.fragment.FragmentOhmsLawLabSimulation;
import com.nyongconnect.android.virtuallab.fragment.FragmentOhmsLawResult;
import com.nyongconnect.android.virtuallab.fragment.FragmentOhmsLawSelfStudy;
import com.nyongconnect.android.virtuallab.fragment.FragmentReflectionLaw;
import com.nyongconnect.android.virtuallab.fragment.FragmentReflectionLawResult;
import com.nyongconnect.android.virtuallab.fragment.FragmentSimplePendulumPractical;
import com.nyongconnect.android.virtuallab.fragment.FragmentSimplePendulumResult;
import com.nyongconnect.android.virtuallab.fragment.FragmentSpiralSpringSimulation;

public  class LabWorkPagerAdapter extends FragmentPagerAdapter {
    private final static int NUM_ITEMS = 2;

    private final CharSequence[] title = {"Lab Practical","Lab Result"};
    private final String code = "001";

    public LabWorkPagerAdapter(FragmentManager fragmentManager,Boolean c) {
        super(fragmentManager);

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                if (LabWorkActivity.code.equals("001")) {
                    return new FragmentOhmsLawLabSimulation();
                }

                if (LabWorkActivity.code.equals("002")) {
                    return new FragmentReflectionLaw();

//                    return new FragmentOhmsLawLabSimulation();
                }


                if (LabWorkActivity.code.equals("003")) {
                    return new FragmentSimplePendulumPractical();

//                    return new FragmentOhmsLawLabSimulation();
                }
            case 1: // Fragment # 0 - This will show FirstFragment different title

                if (LabWorkActivity.code.equals("001")) {
                    return new FragmentOhmsLawResult();
                }

                if (LabWorkActivity.code.equals("002")) {

                    return new FragmentReflectionLawResult();

//                    return new FragmentOhmsLawLabSimulation();
                }


                if (LabWorkActivity.code.equals("003")) {

                    return new FragmentSimplePendulumResult();

//                    return new FragmentOhmsLawLabSimulation();
                }


            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}