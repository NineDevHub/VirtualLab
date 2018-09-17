package com.nyongconnect.android.virtuallab.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.OhmsLawResult;
import com.nyongconnect.android.virtuallab.Pendulum;
import com.nyongconnect.android.virtuallab.R;

import java.util.List;

/**
 * Created by enyason on 9/8/18.
 */

public class PendulumResultArrayAdapter extends ArrayAdapter {

    List<Pendulum> results;
    Context ctx;

    TextView tvlength, tvTime,tvPeriod,tvTsquare;
    public PendulumResultArrayAdapter(@NonNull Context context, @NonNull List<Pendulum> objects) {
        super(context, 0, objects);
        results = objects;
        ctx = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.row_item_pendulum_result,parent,false);
        }

        tvlength = convertView.findViewById(R.id.tv_pendulum_result_length);

        tvTime = convertView.findViewById(R.id.tv_pendulum_result_time);

        tvPeriod = convertView.findViewById(R.id.tv_pendulum_result_period);


        tvTsquare = convertView.findViewById(R.id.tv_pendulum_result_period_square);


        Pendulum pendulumResult = results.get(position);

        tvlength.setText(pendulumResult.getLength()+"");
        tvTime.setText(pendulumResult.getTime()+"");
        tvPeriod.setText(pendulumResult.getPeriod()+"");
        tvTsquare.setText(pendulumResult.gettSquare()+"");



        return convertView;
    }






    @Override
    public int getCount() {
        return results.size();
    }
}
