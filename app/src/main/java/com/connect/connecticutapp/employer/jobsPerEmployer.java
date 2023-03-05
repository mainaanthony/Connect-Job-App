package com.connect.connecticutapp.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.connect.connecticutapp.MyAdapter;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.JobsPage;
import com.connect.connecticutapp.model.RecyclerViewInterface;
import com.connect.connecticutapp.user_profile;
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

public class jobsPerEmployer extends AppCompatActivity implements RecyclerViewInterface  {
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    FirebaseAuth mJob;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Data> list;
    JobsPerEmployerAdapter adapter;
    String jobcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_per_employer);


        recyclerView = findViewById(R.id.rv1_employer);
        mJob = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("JobPost");
        jobcontact = getIntent().getStringExtra("jobContact");
         System.out.println("maviiiiiii"+ jobcontact);
//        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter =new JobsPerEmployerAdapter(this,list, this);
//        recyclerView.setAdapter(adapter);




        //super options

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(reference.orderByChild("jobContact").equalTo(jobcontact), Data.class)
                        .build();


                   adapter = new JobsPerEmployerAdapter(options);
                   recyclerView.setAdapter(adapter);


//implements RecyclerViewInterface
        //Database
//        Query checkUser = FirebaseDatabase.getInstance().getReference("JobPost").orderByChild("jobContact").equalTo(jobcontact);
//
//        reference.addValueEventListener(new ValueEventListener() {
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
//                        Data data = dataSnapshot.getValue(Data.class);
//                        list.add(data);
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
//
//
    }

    @Override
    public void onItemClick(int position) {



        Intent intent = new Intent(jobsPerEmployer.this, JobsPage.class);


        intent.putExtra("jobTitle", list.get(position).getJobTitle());
        intent.putExtra("jobContact", list.get(position).getJobContact());
        intent.putExtra("jobSalary", list.get(position).getJobSalary());
        intent.putExtra("JobSkills", list.get(position).getJobSkills());
        intent.putExtra("JobDescription", list.get(position).getJobDescription());

//        intent.putExtra("fullName",full);
        startActivity(intent);


   }


   //start and stop
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}