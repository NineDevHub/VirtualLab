package com.nyongconnect.android.virtuallab.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enyason on 9/8/18.
 */

public class OhmsLawArrayAdapter extends ArrayAdapter {

    List<OhmsLawResult> results;
    Context ctx;

    TextView tvVoltage, tvCurrent;
    public OhmsLawArrayAdapter(@NonNull Context context, @NonNull List<OhmsLawResult> objects) {
        super(context, 0, objects);
        results = objects;
        ctx = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.row_item_ohms_law_result,parent,false);
        }

        tvVoltage = convertView.findViewById(R.id.tv_ohms_laww_result_voltage);

        tvCurrent = convertView.findViewById(R.id.tv_ohms_laww_result_current);

        OhmsLawResult ohmsLawResult = results.get(position);

        tvVoltage.setText(ohmsLawResult.getVoltage()+"v");

        tvCurrent.setText(ohmsLawResult.getCurrent()+"mA");



        return convertView;
    }






    @Override
    public int getCount() {
        return results.size();
    }
}
