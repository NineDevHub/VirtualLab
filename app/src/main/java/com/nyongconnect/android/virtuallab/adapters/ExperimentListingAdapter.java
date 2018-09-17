package com.nyongconnect.android.virtuallab.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.Physics;
import com.nyongconnect.android.virtuallab.R;

import java.util.List;

public class ExperimentListingAdapter extends RecyclerView.Adapter<ExperimentListingAdapter.ExperimentListingViewHolder>{

    private final List<Physics> list;
    String[] experimentNames = {"Reflection Law", "Ohms Law"};
    int[] images = {R.drawable.ohms,R.drawable.reflection,R.drawable.pendulum};
    public final ExperimentListingClickListener experimentListingClickListener;

   public ExperimentListingAdapter(ExperimentListingClickListener experimentListingClickListener, List<Physics> physicsList){
       list = physicsList;
       this.experimentListingClickListener = experimentListingClickListener;
   }

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

       Physics physics = list.get(position);

        holder.mExperimentName.setText(physics.getTitle());

        holder.mExperimentLogo.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public void clearList() {

        list.clear();
//        notifyDataSetChanged();

   }



    public interface ExperimentListingClickListener{
        void onExperimentListingClickListener(int clickedIndex);
    }
    class ExperimentListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mExperimentName;
        ImageView mExperimentLogo;


        public ExperimentListingViewHolder(View itemView) {

            super(itemView);
            mExperimentName = itemView.findViewById(R.id.tv_eperiment_name);
            mExperimentLogo = itemView.findViewById(R.id.iv_experiment_logo);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            experimentListingClickListener.onExperimentListingClickListener(adapterPosition);
        }
    }
}
