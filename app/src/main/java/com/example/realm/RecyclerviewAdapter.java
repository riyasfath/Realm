package com.example.realm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.realm.Realm;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    List<DataModel> dataModelList;
    Context context;
    ViewHolder holder;
    Realm realm;


    public RecyclerviewAdapter(List<DataModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_course_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder holder, int position) {

        DataModel model = dataModelList.get(position);

        holder.vh_CoureseTrack.setText(model.getCourseTracks());
        holder.vh_CoureseDuration.setText(model.getCourseDuration());
        holder.vh_CoureseName.setText(model.getCourseName());
        holder.vh_CoureseDesc.setText(model.getCourseDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // on below line we are creating a new intent.
                Intent i = new Intent(context, UpdateActivity.class);
                // on below line we are passing all the data to new activity.
                i.putExtra("courseName", model.getCourseName());
                i.putExtra("courseDescription", model.getCourseDescription());
                i.putExtra("courseDuration", model.getCourseDuration());
                i.putExtra("courseTracks", model.getCourseTracks());
                i.putExtra("id", model.getId());
                // on below line we are starting a new activity.
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        private TextView vh_CoureseName;
        private TextView vh_CoureseDuration;
        private TextView vh_CoureseTrack;
        private TextView vh_CoureseDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vh_CoureseName = itemView.findViewById(R.id.cv_tv_CourseName);
            vh_CoureseDuration = itemView.findViewById(R.id.cv_tv_CourseDuration);
            vh_CoureseTrack = itemView.findViewById(R.id.cv_tv_CourseTrack);
            vh_CoureseDesc = itemView.findViewById(R.id.cv_tv_CourseDescription);







        }
    }


}
