package com.connect.connecticutapp.comments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.comment;
import com.connect.connecticutapp.reviews.jobsReviews;
import com.connect.connecticutapp.reviews.reviewsAdapterClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reviewsMainactivity extends AppCompatActivity {

    private TextView jobTitle, companyName;
    private RatingBar ratingBar, jobRating;
    private RecyclerView recyclerView;
    private ArrayList<comment> reviewArrayList;
    private adapterReviewsSecond reviewsSecond;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private String jTitle, con_t_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_mainactivity);


        con_t_act = getIntent().getStringExtra("jobContact");
        System.out.println("contact"+con_t_act);
        jTitle = getIntent().getStringExtra("jobTitle");
        System.out.println("whalaaaa"+jTitle);
        jobTitle = findViewById(R.id.JobTitleReviewsTouche);
        companyName = findViewById(R.id.companyNameReviewsTouche);

        jobRating = findViewById(R.id.ratingBarTouche);
        recyclerView = findViewById(R.id.recyclerReviewsTouche);

        firebaseAuth = FirebaseAuth.getInstance();
        loadReviews();
        loadDetails();








    }

    private void loadDetails() {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("JobPost");
        Query dref = readRef.orderByChild("jobTitle").equalTo(jTitle);
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String cName = ""+snapshot.child("companyName").getValue();
                String jTitle = ""+snapshot.child("jobTitle").getValue();

                companyName.setText(cName);
                jobTitle.setText(jTitle);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private float ratingSum = 0;
    private void loadReviews() {

        reviewArrayList = new ArrayList<>();

          DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments");
          Query query = reference.orderByChild("contact").equalTo(con_t_act);

          query.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  reviewArrayList.clear();
                  ratingSum = 0;

                  for(DataSnapshot ds:snapshot.getChildren()){
                      Float rating = Float.parseFloat(""+ds.child("rating").getValue());


                      comment cment = ds.getValue(comment.class);
                      reviewArrayList.add(cment);

                  }

                  reviewsSecond = new adapterReviewsSecond(reviewsMainactivity.this, reviewArrayList);
                  recyclerView.setAdapter(reviewsSecond);

                  long numberOfReviews = snapshot.getChildrenCount();
                  float avgRating = ratingSum/numberOfReviews;

//                    jobRating.set(String.format("%.2f",avgRating)+"("+numberOfReviews+")");
                  jobRating.setRating(avgRating);

              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });

    }
}