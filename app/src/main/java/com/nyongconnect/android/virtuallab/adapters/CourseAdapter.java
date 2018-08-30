package com.nyongconnect.android.virtuallab.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyongconnect.android.virtuallab.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseAdapterViewHolder>{
    String [] courses = {"physics", "Chemistry"};
    String [] logo = {"phyics.png", "chem.png"};
    private final ListItemClickListener listItemClickListener;

    public CourseAdapter(ListItemClickListener listItemClickListener){
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public CourseAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int courseXml = R.layout.course_list;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(courseXml, parent, false);
        return new CourseAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapterViewHolder holder, int position) {
        String course = courses[position];
        String courseLogo = logo[position];
        holder.courseTitle.setText(course);
        holder.courseLogo.setImageResource(R.drawable.physics);
    }

    @Override
    public int getItemCount() {
        if (courses.length != 0){
            return courses.length;
        }
        else{
        return 0;}
    }

    public interface ListItemClickListener{
        void onListItemClickListener(int clickedIndex);
    }

    class CourseAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView courseLogo;
        TextView courseTitle;

        public CourseAdapterViewHolder(View itemView) {
            super(itemView);
            courseLogo = itemView.findViewById(R.id.iv_course_logo);
            courseTitle = itemView.findViewById(R.id.tv_courses);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterIndex = getAdapterPosition();
            listItemClickListener.onListItemClickListener(adapterIndex);
        }
    }
}
