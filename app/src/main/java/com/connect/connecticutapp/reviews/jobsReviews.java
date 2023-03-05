package com.connect.connecticutapp.reviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.connect.connecticutapp.MyAdapter;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.comment;
import com.connect.connecticutapp.model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class jobsReviews extends AppCompatActivity {


    private TextView jobTitle, companyName;
    private RatingBar ratingBar, jobRating;
    private RecyclerView recyclerView;
    private ArrayList<comment> reviewArrayList;
    private reviewsAdapterClass reAdapterClass;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private String jTitle, con_t_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_reviews);


        jTitle = getIntent().getStringExtra("jobTitle");
        System.out.println("whalaaaa" + jTitle);
        reviewArrayList = new ArrayList<>();
        jobTitle = findViewById(R.id.JobTitleReviews);
        companyName = findViewById(R.id.companyNameReviews);
        ratingBar = findViewById(R.id.ratingPerReview);
        jobRating = findViewById(R.id.ratingBar);
        recyclerView = findViewById(R.id.recyclerComments);


        con_t_act = getIntent().getStringExtra("jobContact");
        System.out.println("contact" + con_t_act);


        firebaseAuth = FirebaseAuth.getInstance();
        loadReviews();
        loadDetails();


    }

    private void loadDetails() {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dref = readRef.child("JobPost");
        Query query = dref.orderByChild("jobTitle").equalTo(jTitle);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                        jobType.setText((ds.child("jobType").getValue().toString()));
//                        companyName.setText((ds.child("jobTitle").getValue().toString()));
                        companyName.setText((ds.child("jobLocation").getValue().toString()));
                        jobTitle.setText((ds.child("jobTitle").getValue().toString()));
//                        jobContact.setText((ds.child("jobContact").getValue().toString()));
//                        jobSkills.setText((ds.child("jobSkills").getValue().toString()));
//                        jobCert.setText((ds.child("jobCertificates").getValue().toString()));
//                        jobDescription.setText((ds.child("jobDescription").getValue().toString()));
//                        jobExperience.setText((ds.child("jobSkills").getValue().toString()));

                    }
                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        });


    }

    private float ratingSum = 0;

    private void loadReviews() {

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference("Comments");
        Query query = readRef.orderByChild("contact").equalTo(con_t_act);
//        query

//        DatabaseReference dref = readRef.child("Comments");
//        Query query = dref.orderByChild("rating");//.equalTo(jTitle);//////////////////////.orderByChild("contact").equalTo(con_t_act)


//        Query checkUser = FirebaseDatabase.getInstance().getReference("Comments");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //reviewArrayList.clear();
                ratingSum = 0;
//                if(dataSnapshot.exists()) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                        float rating =Float.parseFloat(""+ds.child("rating").getValue());
                    String rating = ds.child("rating").getValue(String.class);
                    System.out.println("womenzzz " + rating);
                    float f1 = Float.parseFloat(rating);
                    ratingSum = ratingSum + f1;

                    String fullName= ds.child("fullName").getValue(String.class);
                    String commentPost= ds.child("commentPost").getValue(String.class);
                    String contact= ds.child("contact").getValue(String.class);
                    String timestamp= ds.child("timestamp").getValue(String.class);
                    reviewArrayList.add(new comment(fullName,commentPost,contact,rating,timestamp));
                    System.out.println("nAME: "+fullName+" / post: "+commentPost+ " / contact: "+contact+" / timestamp: "+timestamp);


                }
                recyclerView.setLayoutManager(new LinearLayoutManager(jobsReviews.this));
                reAdapterClass = new reviewsAdapterClass(jobsReviews.this, reviewArrayList);
                recyclerView.setAdapter(reAdapterClass);
                recyclerView.setHasFixedSize(true);


                long numberOfReviews = dataSnapshot.getChildrenCount();
                float avgRating = ratingSum / numberOfReviews;

//                    jobRating.set(String.format("%.2f",avgRating)+"("+numberOfReviews+")");
                jobRating.setRating(avgRating);
            }

//                else
//                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
//
//            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        });


    }
}