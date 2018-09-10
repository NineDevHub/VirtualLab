package com.nyongconnect.android.virtuallab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.nyongconnect.android.virtuallab.R;

public class FragmentReflectionLaw extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner spinner; ReflectionLawView ref;

    public FragmentReflectionLaw() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_reflection_law, container, false);
    }

    public void startSimulation(View view) {
        spinner = view.findViewById(R.id.spinner_list);
        ref = view.findViewById(R.id.reflection_law);
        try {
            ref.incidence = Float.parseFloat(spinner.getSelectedItem().toString());
        }
        catch (Exception e){
            Toast.makeText(getActivity(), "select angle", Toast.LENGTH_LONG).show();
        }

    }


}
