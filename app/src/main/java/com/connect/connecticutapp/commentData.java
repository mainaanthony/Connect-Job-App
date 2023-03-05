package com.connect.connecticutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.RecyclerViewInterface;

import java.util.ArrayList;

public class commentData extends RecyclerView.Adapter<commentData.MyViewHolder>
{

    private  final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<comment> list;

    public commentData(Context context, ArrayList<comment> list, RecyclerViewInterface recyclerViewInterface) {

        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public commentData.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.comments, parent, false);
        return new commentData.MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull commentData.MyViewHolder holder, int position) {
        comment com = list.get(position);
        holder.name.setText(com.getFullName());
        holder.comment_post.setText(com.getCommentPost());
//        holder.salary.setText(data.getJobSalary());
//        holder.skills.setText(data.getJobSkills());
//        holder.description.setText(data.getJobDescription());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,comment_post;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.post_comment_name);
            comment_post = itemView.findViewById(R.id.post_comment_textview);
//            salary = itemView.findViewById(R.id.post_salary);
//            skills = itemView.findViewById(R.id.post_skills);
//            description = itemView.findViewById(R.id.post_description);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int pos = getBindingAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });

        }
    }


}
