package com.nyongconnect.android.virtuallab.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.R;

public class ExperimentListingAdapter extends RecyclerView.Adapter<ExperimentListingAdapter.ExperimentListingViewHolder>{

    String[] experimentNames = {};
    @NonNull
    @Override
    public ExperimentListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutXml = R.layout.experiment_listing;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutXml, parent, false);

        return new ExperimentListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperimentListingViewHolder holder, int position) {
        String experiment = experimentNames[position];
        holder.mExperimentName.setText(experiment);
    }

    @Override
    public int getItemCount() {
        if (experimentNames.length != 0){
            return experimentNames.length;
        }
        return 0;
    }

    class ExperimentListingViewHolder extends RecyclerView.ViewHolder{
        TextView mExperimentName;


        public ExperimentListingViewHolder(View itemView) {

            super(itemView);
            mExperimentName = itemView.findViewById(R.id.tv_eperiment_name);

        }
    }
}
