package com.nyongconnect.android.virtuallab.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;
import com.nyongconnect.android.virtuallab.adapters.OhmsLawArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentSpiralSpringResult extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentSpiralSpringResult() {
        // Required empty public constructor
    }


    List<OhmsLawResult> ohmsLawResults;
    static OhmsLawArrayAdapter arrayAdapter;

    ListView listView;

    RelativeLayout layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ohmsLawResults = new ArrayList<>();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ohms_law_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ohmsLawResults.add(new OhmsLawResult("8","0.008"));
//        ohmsLawResults.add(new OhmsLawResult("9","0.009"));
//        ohmsLawResults.add(new OhmsLawResult("10","0.01"));

        layout = view.findViewById(R.id.layout_ohms_result);
        if (!LabWorkActivity.isInit) {

            layout.setVisibility(View.INVISIBLE);
//            Toast.makeText(getContext(),"Experiment not Initialized...",Toast.LENGTH_LONG).show();
            return;

        }

        Log.i("Size "," "+ohmsLawResults.size());

        arrayAdapter = new OhmsLawArrayAdapter(getContext(),ohmsLawResults);

        listView = view.findViewById(R.id.list_ohms_law_result);

        listView.setAdapter(arrayAdapter);
//        arrayAdapter.notifyDataSetChanged();


    }

    public  static void updateTableInfo(OhmsLawResult result){
        arrayAdapter.add(result);
        arrayAdapter.notifyDataSetChanged();


    }

}
