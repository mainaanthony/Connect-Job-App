package com.connect.connecticutapp.Articles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.employer.JobsPerEmployerAdapter;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.RecyclerViewInterface;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class articlePerJobSeeker extends AppCompatActivity implements RecyclerViewInterface {

    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    FirebaseAuth mJob;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ArticlesData> list;
    articlesPerJobSeekerAdapter adapter;
    String art_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_per_job_seeker);


        recyclerView = findViewById(R.id.rv1_article);
        mJob = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("Article");
        art_contact = getIntent().getStringExtra("jobSeekerContact");
        System.out.println("maviiiiiii"+ art_contact);


//        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter =new articlesPerJobSeekerAdapter(this,list, this);
//        recyclerView.setAdapter(adapter);


        //super options

        FirebaseRecyclerOptions<ArticlesData> options =
                new FirebaseRecyclerOptions.Builder<ArticlesData>()
                        .setQuery(reference.orderByChild("jobContact").equalTo(art_contact), ArticlesData.class)
                        .build();


        adapter = new articlesPerJobSeekerAdapter(options);
        recyclerView.setAdapter(adapter);


//        //Database
//        Query checkUser = FirebaseDatabase.getInstance().getReference("Article").orderByChild("writerContact").equalTo(art_contact);
//
//        checkUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//
//
////                    String systemPassword = snapshot.child("jobContact").getValue(String.class);
////                    if (systemPassword==jobcontact) {
////
////
//
//                    ArticlesData data = dataSnapshot.getValue(ArticlesData.class);
//                    list.add(data);
//
////                        String _fullname = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
////                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
////                        String _phoneNo = snapshot.child(_completePhoneNumber).child("phoneNO").getValue(String.class);
////                        String _dateOfBirth = snapshot.child(_completePhoneNumber).child("date").getValue(String.class);
//
//
////                        Toast.makeText(loginEmployer.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth, Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(getApplicationContext(), user_profile.class);
////                        startActivity(intent);
////                        finish();
//
////                    }
////                    else{
////
////                        Toast.makeText(jobsPerEmployer.this, "password does not match", Toast.LENGTH_SHORT).show();
////
////                    }
//
//
//
//
//
////                   String ma = snapshot.child("jobTitle").getValue().toString();
////                   System.out.println("The jobs are"+ma);
//
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }


    @Override
    public void onItemClick(int position) {

    }
}