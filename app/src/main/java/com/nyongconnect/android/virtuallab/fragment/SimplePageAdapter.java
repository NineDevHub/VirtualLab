package com.nyongconnect.android.virtuallab.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nyongconnect.android.virtuallab.activities.StudentActivity;

public class SimplePageAdapter extends FragmentPagerAdapter {
    int pos;
    public SimplePageAdapter(StudentActivity studentActivity, FragmentManager fm) {
        super(fm);
        pos = studentActivity.pos;
    }



    @Override
    public Fragment getItem(int position) {
        if (pos==0){
            if (position == 0){
                return new FragmentReflectionLaw();
            }
            else {
                return new FragmentReflectionLawResult();
            }
        }
//        else if(pos == 2){
//            if (position == 0){
//                return new FragmentSimplePendulumPractical();
//            }
//            else {
//                return new FragmentSimplePendulumResult();
//            }
       // }
        else return new FragmentReflectionLaw();


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Practical";
            case 1:
                return "Result";
                default:
                    return "Practical";
        }

    }
}

