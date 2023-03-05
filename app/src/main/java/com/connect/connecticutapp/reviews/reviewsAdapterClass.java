package com.connect.connecticutapp.reviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.MyAdapter;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.comment;

//import java.text.DateFormat;
import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class reviewsAdapterClass extends  RecyclerView.Adapter<reviewsAdapterClass.HolderReview>{


    private Context context;
    private ArrayList<comment> reviewArrayList;

    public reviewsAdapterClass(Context context, ArrayList<comment> reviewArrayList) {
        this.context = context;
        this.reviewArrayList = reviewArrayList;
    }

    @NonNull
    @Override
    public HolderReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments, parent, false);
        return new HolderReview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderReview holder, int position) {

        comment cment = reviewArrayList.get(position);
//        String name = cment.getFullName();
//        String review = cment.getCommentPost();
        String rate = cment.getRating();
        String timestamp = cment.getTimestamp();


//        convert timestamp into dd/MM/yyyy
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();



        holder.ratingBar.setRating(Float.parseFloat(rate));
        holder.name.setText(cment.getFullName());
        holder.comment.setText(cment.getCommentPost());
        holder.dateReviews.setText(dateFormat);





    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    class HolderReview extends RecyclerView.ViewHolder{
        private TextView name, comment,dateReviews;
        private RatingBar ratingBar;


        public HolderReview(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.post_comment_name);
            comment = itemView.findViewById(R.id.post_comment_textview);
           ratingBar = itemView.findViewById(R.id.ratingPerReview);
            dateReviews = itemView.findViewById(R.id.ReviewsDate);


        }
    }

}
