package com.connect.connecticutapp.jobPerEmployer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.model.Data;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class jobPerEmployerMain extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    jobperEmployeradapterTesr employeradapterTesr;
    String jobcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_per_employer_main);

        jobcontact = getIntent().getStringExtra("jobContact");
        System.out.println("maviiiiiii"+ jobcontact);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("JobPost");
        Query query = databaseReference.orderByChild("jobContact").equalTo(jobcontact);
        recyclerView = findViewById(R.id.rv1_employer_test);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(query,Data.class)
                        .build();
//.orderByChild("jobContact").equalTo(jobcontact)

        employeradapterTesr = new jobperEmployeradapterTesr(options);
        recyclerView.setAdapter(employeradapterTesr);


    }

    @Override
    protected void onStart() {
        super.onStart();
        employeradapterTesr.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        employeradapterTesr.stopListening();
    }
}