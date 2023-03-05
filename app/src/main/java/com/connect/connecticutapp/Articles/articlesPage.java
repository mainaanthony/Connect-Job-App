package com.connect.connecticutapp.Articles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.comment;
import com.connect.connecticutapp.commentData;
import com.connect.connecticutapp.reviews.jobsReviews;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class articlesPage extends AppCompatActivity {
    RelativeLayout progressbar;
    TextView articleTitle, articleWriter_name,articleContact, articleWriter_Email, articleWritten_desc, articleWritten_post, articleWriterBrief_desc;
    TextInputLayout comment_write, confirm_contact;
    String searchID, full, con_t_act, strDate, Expirydate, result;
    Button article_comment_post, article_reviewsButton, favoriteButton;
    RatingBar ratingBar;
    FirebaseAuth job;
    RecyclerView recyclerView;
//    ArrayList<comment> list;
//    commentData adapter;





    //firebase
    private FirebaseAuth mAuth;
    DatabaseReference mJobPOst;

    //expiryDate

    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_page);


        con_t_act = getIntent().getStringExtra("jobContact");
        System.out.println("comentiiii" + con_t_act);

        articleTitle = findViewById(R.id.article_name);

        articleWriter_name = findViewById(R.id.article_WriterName);
        articleWriter_Email = findViewById(R.id.articleWriterEmail);
        articleContact = findViewById(R.id.writerContact);
        articleWritten_desc = findViewById(R.id.articleDescription);
        articleWritten_post = findViewById(R.id.articlePost);
        articleWriterBrief_desc = findViewById(R.id.articleWriterInformation);
        progressbar = findViewById(R.id.progressbarArticles);
        comment_write = findViewById(R.id.article_edit);
        article_comment_post = findViewById(R.id.article_comment_post);
        confirm_contact = findViewById(R.id.article_confirm_contact);
        recyclerView = findViewById(R.id.rv_article);
        ratingBar = findViewById(R.id.ratingBarArticles);
        article_reviewsButton= findViewById(R.id.article_reviewsButton);
//        favoriteButton = findViewById(R.id.AddRemoveButton);

//        full = getIntent().getStringExtra("fullName");





        full = getIntent().getStringExtra("fullName");
        System.out.println("kaliiii" + full);

        searchID = getIntent().getStringExtra("articleTitle");
        System.out.println("umaviii"+ searchID);


        article_reviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), jobsReviews.class);
                intent.putExtra("jobTitle", searchID);
                intent.putExtra("jobContact", con_t_act);
                startActivity(intent);
            }
        });
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dref = readRef.child("Article");
        Query query = dref.orderByChild("articleTitle").equalTo(searchID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                     String timestamp =  dataSnapshot.child("timestamp").getValue(String.class);
                        System.out.println("samtaimuuu"+timestamp);


//                        long time=Long.parseLong(timestamp);
//
//
//                        //        convert timestamp into dd/MM/yyyy
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.setTimeInMillis(Long.parseLong(String.valueOf(time)));
//                        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();
//                        System.out.println("wuuuuuuu"+dateFormat);




                        String full =  dataSnapshot.child("writerName").getValue(String.class);
                        String fullName = full;

                        articleTitle.setText((ds.child("articleTitle").getValue().toString()));
//                        companyName.setText((ds.child("jobTitle").getValue().toString()));
                        articleWriter_name.setText((ds.child("writerName").getValue().toString()));
                        articleWriter_Email.setText((ds.child("writerEmail").getValue().toString()));
                        articleWritten_desc.setText((ds.child("articleDescription").getValue().toString()));
                        articleWritten_post.setText((ds.child("articleContent").getValue().toString()));
                        articleWriterBrief_desc.setText((ds.child("writerDesc").getValue().toString()));

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

    public void comment(View view) {
        mJobPOst = FirebaseDatabase.getInstance().getReference();
//        String fullName = full;


        searchID = getIntent().getStringExtra("articleTitle");
        System.out.println("umaviii"+ searchID);
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dref = readRef.child("Article");
        Query query = dref.orderByChild("articleTitle").equalTo(searchID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                     String timestamp =  dataSnapshot.child("timestamp").getValue(String.class);
//                        long time=Long.parseLong(timestamp);
//                     System.out.println("samtaimuuu"+timestamp);
//
//                        //        convert timestamp into dd/MM/yyyy
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.setTimeInMillis(Long.parseLong(String.valueOf(time)));
//                        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();
//                        System.out.println("wuuuuuuu"+dateFormat);




                        String full =  dataSnapshot.child("writerName").getValue(String.class);
                        String fullName = full;

                        System.out.println("rttttttt" + full);

                        String id = mJobPOst.push().getKey();
                        String commentPost = comment_write.getEditText().getText().toString().trim();
                        String contact = confirm_contact.getEditText().getText().toString().trim();
                        String rating = "" + ratingBar.getRating();

                        String timestamp = "" + System.currentTimeMillis();

                        articleReviewsData comm = new articleReviewsData(fullName, commentPost, contact, rating, timestamp);
                        mJobPOst.child("Reviews").child(id).setValue(comm);



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

    public void seeReviews(View view) {
    }
}