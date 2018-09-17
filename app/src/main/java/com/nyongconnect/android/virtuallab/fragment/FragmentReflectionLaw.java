package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.R;
import com.nyongconnect.android.virtuallab.activities.LabWorkActivity;

public class FragmentReflectionLaw extends Fragment {

    Spinner spinner;
    ReflectionLawView ref;

    Button buttonSimulate;
    private RelativeLayout layout;

    public FragmentReflectionLaw() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_reflection_law, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        layout = view.findViewById(R.id.layout_reflection);

        if (!LabWorkActivity.isInit) {

            layout.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"Experiment not Initialized...",Toast.LENGTH_LONG).show();
            return;

        }

        buttonSimulate = view.findViewById(R.id.btn_reflection_start);


        spinner = view.findViewById(R.id.spinner_list);
        ref = view.findViewById(R.id.reflection_law);

        buttonSimulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSimulation();


            }
        });
    }


    public void startSimulation() {

        try {
            ref.incidence = Float.parseFloat(spinner.getSelectedItem().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "select angle", Toast.LENGTH_LONG).show();
        }

    }


}
