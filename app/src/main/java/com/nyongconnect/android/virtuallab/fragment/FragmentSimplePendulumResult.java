package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.Pendulum;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;
import com.nyongconnect.android.virtuallab.adapters.OhmsLawArrayAdapter;
import com.nyongconnect.android.virtuallab.adapters.PendulumResultArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentSimplePendulumResult extends Fragment {

    private View layout;


    List<Pendulum> pendulumList;
    static PendulumResultArrayAdapter arrayAdapter;

    ListView listView;


    public FragmentSimplePendulumResult() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        pendulumList = new ArrayList<>();

        return inflater.inflate(R.layout.fragment_fragment_simple_pendulum_result, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = view.findViewById(R.id.layout_pendulum_result);
        if (!LabWorkActivity.isInit) {

            layout.setVisibility(View.INVISIBLE);
//            Toast.makeText(getContext(),"Experiment not Initialized...",Toast.LENGTH_LONG).show();
            return;

        }



        Log.i("Size "," "+pendulumList.size());

        arrayAdapter = new PendulumResultArrayAdapter(getContext(),pendulumList);

        listView = view.findViewById(R.id.list_pendulum_result);

        listView.setAdapter(arrayAdapter);


    }

    public static void updateTableInfo(Pendulum result) {

        arrayAdapter.add(result);
        arrayAdapter.notifyDataSetChanged();

    }
}
