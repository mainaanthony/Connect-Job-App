package com.connect.connecticutapp.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.reviews.jobsReviews;
import com.connect.connecticutapp.user_profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

//import java.text.DateFormat;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class JobsPage extends AppCompatActivity implements RecyclerViewInterface {
    RelativeLayout progressbar;
    TextView jobName, jobContact, jobSkills, jobLocation, jobType, jobDescription, companyName, jobExperience, jobCert, jobExpiry;
    TextInputLayout comment_write, confirm_contact;
    String searchID, full, con_t_act, strDate, Expirydate, result, Timestamp, website;
    Button post_comment, reviews, favoriteButton;
    RatingBar ratingBar;
    FirebaseAuth job;
    RecyclerView recyclerView;
    ArrayList<comment> list;
    commentData adapter;
    String id;
    //firebase
    private FirebaseAuth mAuth;
    DatabaseReference mJobPOst;

    //expiryDate

    Runnable runnable;
    Handler handler;

    //counter
    int counter = 0;


    @Override
    protected void onStart() {
        super.onStart();


        con_t_act = getIntent().getStringExtra("jobContact");
        System.out.println("comentiiii" + con_t_act);

        jobName = findViewById(R.id.job_name);

        jobContact = findViewById(R.id.job_contact);
        jobLocation = findViewById(R.id.company_location);
//        companyName = findViewById(R.id.company_name);
        jobSkills = findViewById(R.id.Experiences_required);
        jobDescription = findViewById(R.id.job_description_qualification);
        jobType = findViewById(R.id.job_type);
        jobCert = findViewById(R.id.Education_certificates);
        jobExperience = findViewById(R.id.Experiences_required);
        progressbar = findViewById(R.id.progressbarJobPage);
        comment_write = findViewById(R.id.comment_edit);
        post_comment = findViewById(R.id.comment_post);
        confirm_contact = findViewById(R.id.job_confirm_contact);
        recyclerView = findViewById(R.id.rv_jobsPage);
        ratingBar = findViewById(R.id.ratingBarJobPage);
        reviews = findViewById(R.id.reviewsButton);
        favoriteButton = findViewById(R.id.AddRemoveButton);

//        full = getIntent().getStringExtra("fullName");

        full = getIntent().getStringExtra("fullName");
        System.out.println("kaliiii" + Timestamp);

        searchID = getIntent().getStringExtra("jobTitle");
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dref = readRef.child("JobPost");
        Query query = dref.orderByChild("jobTitle").equalTo(searchID);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        id = ds.getKey();
                        System.out.println("kkkkkkhkh: "+id);
                        Timestamp=ds.child("timestamp").getValue().toString();
                        jobType.setText((ds.child("jobType").getValue().toString()));
//                        companyName.setText((ds.child("jobTitle").getValue().toString()));
                        jobLocation.setText((ds.child("jobLocation").getValue().toString()));
                        jobName.setText((ds.child("jobTitle").getValue().toString()));
                        jobContact.setText((ds.child("jobContact").getValue().toString()));
                        jobSkills.setText((ds.child("jobSkills").getValue().toString()));
                        jobCert.setText((ds.child("jobCertificates").getValue().toString()));
                        jobDescription.setText((ds.child("jobDescription").getValue().toString()));
                        jobExperience.setText((ds.child("jobSkills").getValue().toString()));

                    }
                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        });

//        messages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent beach = new  Intent(user_profile.this, dashboard_activity.class);
//                beach.putExtra("fullName" , _fullName);
//                beach.putExtra("phoneNo", confirm_user);
//                beach.putExtra("email", email);
//                startActivity(beach);
//            }
//        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), jobsReviews.class);
                intent.putExtra("jobTitle", searchID);
                intent.putExtra("jobContact", con_t_act);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_page);
        jobExpiry = findViewById(R.id.expiry);
        Expirydate = getIntent().getStringExtra("date");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        long time = System.currentTimeMillis();


        long timestamp = System.currentTimeMillis();


        //        convert timestamp into dd/MM/yyyy
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(String.valueOf(timestamp)));
        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();

        System.out.println("ytnnnnv" + dateFormat);


        countdownStart(dateFormat);


//        loadExpiry();


    }


    private String countdownStart(String dateformat) {


//        private String getDate(lon  g time) {
        SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date1 = dates.parse(Expirydate);

            Date date2 = dates.parse(dateformat);


            long difference = Math.abs(date1.getTime() - date2.getTime());


            long differenceDates = difference / (24 * 60 * 60 * 1000);


            String dayDifference = Long.toString(differenceDates);
            System.out.println("mashidaa" + dayDifference);

            jobExpiry.setText("The difference between two dates is" + dayDifference + "days");


            if (Integer.parseInt(dayDifference) <= 10) {


                notification();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


//

        return dateformat;

    }


    private void notification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(JobsPage.this, "My Notification");
        builder.setContentTitle("My title");
        builder.setContentText("Your Job expires soon");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(JobsPage.this);
        managerCompat.notify(1, builder.build());


    }

    public void goTo(View view) {


        counter++;



        //start..............................


//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference voteRef = database.getReference("JobPost").child(Timestamp);
//
//// Create a button in your app and set a onClickListener
//
//
//        voteRef.child("clicks").runTransaction(new Transaction.Handler() {
//@Override
//public Transaction.Result doTransaction(MutableData mutableData) {
//        Integer currentValue = mutableData.getValue(Integer.class);
//        if (currentValue == null) {
//        mutableData.setValue(1);
//            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("jobWebsite")));
//            startActivity(launchBrowser);
//        } else {
//        mutableData.setValue(currentValue + 1);
//            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("jobWebsite")));
//            startActivity(launchBrowser);
//        }
//
//        return Transaction.success(mutableData);
//        }
//
//@Override
//public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
//        // Transaction completed
//        }
//        });
//


        ///end.......................

//        DatabaseReference reference =  FirebaseDatabase.getInstance().getReference("Users");
//        reference.child(_phoneNumber).child("password").setValue(_newPassword);
        counter++;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("JobPost").child(id);
        reference.child("clicks").setValue(Integer.valueOf(counter));


        //add reports
        DatabaseReference referee =  FirebaseDatabase.getInstance().getReference("JobPost");

        //get a reference to the Firebase database


//add a listener that will be called each time a button is clicked
        referee.addValueEventListener(new ValueEventListener(){
            public void onDataChange(DataSnapshot dataSnapshot){
                //retrieve the click count values
                long maxCount = 0;
                String maxButton = "";
                String maxJob = "";
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    long count = ds.child("clicks").getValue(Integer.class);
                    //update the highest click count
                    if(count > maxCount){
                        maxCount = count;
//                        maxButton = ds.getKey();
                        maxButton = ds.child("companyName").getValue().toString();
                        maxJob = ds.child("jobTitle").getValue().toString();

                    }
                }
                //print the results
                System.out.println("The company with the Highest No of Applicants is "+maxButton+" with "+maxCount+" clicks"+maxJob);
            }
            public void onCancelled(DatabaseError databaseError) {}
        });








Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("jobWebsite")));
        startActivity(launchBrowser);


    }







    public void comment(View view) {

        mJobPOst = FirebaseDatabase.getInstance().getReference();
        String fullName = full;
        System.out.println("rttttttt" + full);

        String id = mJobPOst.push().getKey();
        String commentPost = comment_write.getEditText().getText().toString().trim();
        String contact = confirm_contact.getEditText().getText().toString().trim();
        String rating = "" + ratingBar.getRating();

        String timestamp = "" + System.currentTimeMillis();

        comment comm = new comment(fullName, commentPost, contact, rating, timestamp);
        mJobPOst.child("Comments").child(id).setValue(comm);


    }


    @Override
    public void onItemClick(int position) {

    }
}

//    //get a reference to the Firebase database
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//
////add a listener that will be called each time a button is clicked
//database.getReference().addValueEventListener(new ValueEventListener(){
//public void onDataChange(DataSnapshot dataSnapshot){
//        //retrieve the click count values
//        long maxCount = 0;
//        String maxButton = "";
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//        long count = ds.child("clicks").getValue(Long.class);
//        //update the highest click count
//        if(count > maxCount){
//        maxCount = count;
//        maxButton = ds.getKey();
//        }
//        }
//        //print the results
//        System.out.println("The button with the highest click count is "+maxButton+" with "+maxCount+" clicks");
//        }
//public void onCancelled(DatabaseError databaseError) {}
//        });
//
////Initialize Firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
////Button
//        Button btnClick = (Button) findViewById(R.id.btnClick);
//
////Button click listener
//        btnClick.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        //Get reference to 'count' node
//        DatabaseReference countRef = database.getReference("count");
//
//        // Increment count
//        countRef.runTransaction(new Transaction.Handler() {
//@Override
//public Transaction.Result doTransaction(MutableData mutableData) {
//        Integer currentValue = mutableData.getValue(Integer.class);
//        if (currentValue == null) {
//        mutableData.setValue(1);
//        } else {
//        mutableData.setValue(currentValue + 1);
//        }
//        return Transaction.success(mutableData);
//        }
//
//@Override
//public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
//        // Transaction completed
//        }
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference voteRef = database.getReference("votes");
//
//// Create a button in your app and set a onClickListener
//        button.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        String name = "John Doe";
//        voteRef.child(name).runTransaction(new Transaction.Handler() {
//@Override
//public Transaction.Result doTransaction(MutableData mutableData) {
//        Integer currentValue = mutableData.getValue(Integer.class);
//        if (currentValue == null) {
//        mutableData.setValue(1);
//        } else {
//        mutableData.setValue(currentValue + 1);
//        }
//
//        return Transaction.success(mutableData);
//        }
//
//@Override
//public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
//        // Transaction completed
//        }
//        });
//        }
//        });
//
//        This could be due to a few different reasons. One possibility is that the layout manager of your RecyclerView is not set up correctly. Another possibility is that the data set of your RecyclerView is too large, causing the RecyclerView to become overwhelmed and unable to show all of the items. Finally, it could be caused by a bug in your code.
//
//        To fix this, you should first check your layout manager to make sure it is set up correctly and that it is not restricting the scrolling. You should also make sure that the data set of your RecyclerView is not too large, or that you have implemented paging to ensure that only a certain amount of items are displayed at one time.
//
//        If these two steps do not fix the issue, then you should review your code for any bugs that could be causing the issue.
//
//        Here is an example of how to use paging with a RecyclerView:
//
//// Create a linear layout manager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//// Set the layout manager of the RecyclerView
//        recyclerView.setLayoutManager(layoutManager);
//
//// Create a paged list adapter
//        PagedListAdapter adapter = new PagedListAdapter(list);
//
//// Set the adapter of the RecyclerView
//        recyclerView.setAdapter(adapter);
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setNestedScrollingEnabled(true);