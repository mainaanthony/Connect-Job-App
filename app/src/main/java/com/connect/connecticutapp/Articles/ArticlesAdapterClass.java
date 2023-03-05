package com.connect.connecticutapp.Articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.RecyclerViewInterface;

import java.util.ArrayList;

public class ArticlesAdapterClass extends RecyclerView.Adapter<ArticlesAdapterClass.MyViewHolder> {
    private  final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<ArticlesData> list;


    public ArticlesAdapterClass(Context context, ArrayList<ArticlesData> list, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }







    @NonNull
    @Override
    public ArticlesAdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.articles, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapterClass.MyViewHolder holder, int position) {
        ArticlesData data = list.get(position);
        holder.name.setText(data.getArticleTitle());
        holder.description.setText(data.getArticleDescription());
        holder.content.setText(data.getWriterContact());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,description, content;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.post_title_articles);
            content = itemView.findViewById(R.id.post_content);
            description = itemView.findViewById(R.id.post_description_articles);



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
//






}
