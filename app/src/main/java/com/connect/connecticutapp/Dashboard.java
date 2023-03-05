package com.connect.connecticutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    private Button employee, jobSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        employee = findViewById(R.id.buttonEmployer);
        jobSeeker = findViewById(R.id.buttonJobseeker);


        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.connect.connecticutapp.employer.loginEmployer.class);
                startActivity(intent);
            }
        });
        jobSeeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), loginJobSeeker.class);
                startActivity(intent);
            }
        });
    }
}