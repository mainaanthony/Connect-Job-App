package com.connect.connecticutapp.localNews;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;
//import com.finder.newsdata.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EstateNewsAdapter extends RecyclerView.Adapter<EstateNewsAdapter.ViewHolder> {

    private final ArrayList<EstateNewsItem> parseItems;
    Context context;

    public EstateNewsAdapter(ArrayList<EstateNewsItem> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EstateNewsItem parseItem = parseItems.get(position);
        String url = parseItem.getImgUrl();
        String text = parseItem.getTitle();
        Picasso.get().load(url).into(holder.imageView);
        holder.textView.setText(text);
        holder.date.setText(parseItem.getContent());
        holder.author.setText(parseItem.getAuthor());


    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView, date, author;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.articleImage);
            textView = view.findViewById(R.id.article_adapter_tv_description);
            date = view.findViewById(R.id.article_adapter_tv_date);
            author = view.findViewById(R.id.article_adapter_tv_author);
            view.setOnClickListener(this);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            EstateNewsItem parseItem = parseItems.get(position);

            Intent intent = new Intent(context, NewsEstateDetailsActivity.class);
            intent.putExtra("title", parseItem.getTitle());
            intent.putExtra("image", parseItem.getImgUrl());
            intent.putExtra("Date", parseItem.getContent());
            intent.putExtra("Author", parseItem.getAuthor());
            intent.putExtra("detailUrl", parseItem.getDetailUrl());
            context.startActivity(intent);
        }
    }


}
