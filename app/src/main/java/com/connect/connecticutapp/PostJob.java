package com.connect.connecticutapp;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.connect.connecticutapp.model.Data;
import com.firebase.client.Firebase;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class PostJob extends AppCompatActivity {

    TextInputLayout job_Title, Job_Desc, Job_Skills, Job_Contact, Job_Salary,Job_Certificates, Job_Location, Job_Type, Job_website, cName;
    Button button1;
    DatePicker datePicker;

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mJobPOst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);


//        mAuth = FirebaseAuth.getInstance();
//
//        FirebaseUser mUser =  mAuth.getCurrentUser();
//
//        String uId = mUser.getUid();
//
//        mJobPOst = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        job_Title = findViewById(R.id.Job_Title);
        Job_Desc = findViewById(R.id.job_description);
        Job_Contact = findViewById(R.id.Organization_Contact);
        Job_Salary = findViewById(R.id.job_salary);
        Job_Skills = findViewById(R.id.job_skill_set_required);
        Job_Certificates = findViewById(R.id.Education_certificates);
        Job_website = findViewById(R.id.website);
        Job_Location = findViewById(R.id.location);
        Job_Type = findViewById(R.id.job_type);
        button1 = findViewById(R.id.post_a_job);
        cName = findViewById(R.id.company_name_post);
        datePicker = findViewById(R.id.age_picker_expiry_job);
                mJobPOst = FirebaseDatabase.getInstance().getReference();







    }
    public void callNextSignupScreen(View view) {



        if (!validateJobName() | !validateJobSkills() | !validateJobSalary() | !validateJobContact() | !validateJobDesc()) {
            return;
        }




        String jobTitle = job_Title.getEditText().getText().toString().trim();
        String job_title = getIntent().getStringExtra("jobTitle");
        String jobDescription =Job_Desc.getEditText().getText().toString().trim();
        String jobSalary = Job_Salary.getEditText().getText().toString().trim();
        String salary = getIntent().getStringExtra("JobSalary");
        String jobSkills = Job_Skills.getEditText().getText().toString().trim();
        String job_description = getIntent().getStringExtra("JobDesc");
        String skills = getIntent().getStringExtra("JobSkills");
        String jobContact = Job_Contact.getEditText().getText().toString().trim();
        String contact = getIntent().getStringExtra("JobContact");
        String companyName = cName.getEditText().getText().toString().trim();
        String jobCertificates = Job_Certificates.getEditText().getText().toString().trim();
        String jobLocation = Job_Location.getEditText().getText().toString().trim();
        String jobType = Job_Type.getEditText().getText().toString().trim();
        String jobWebsite = Job_website.getEditText().getText().toString().trim();




        String id = mJobPOst.push().getKey();
        String timestamp = ""+System.currentTimeMillis();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String date = day+"/"+month+"/"+year;

        int clicks =0;


        Data data = new Data(jobTitle,jobContact,jobSalary,jobSkills,jobDescription,jobCertificates,jobLocation,jobType,jobWebsite, timestamp, companyName, date, clicks);

           mJobPOst.child("JobPost").child(id).setValue(data);







        Intent intent = new Intent(getApplicationContext(), dashboard_activity.class);

        intent.putExtra("JobContact", jobContact);
        System.out.println("wewe: "+jobContact);
        intent.putExtra("Id", id);
//        intent.putExtra("date", date);
        intent.putExtra("JobSkills", jobSkills);
        intent.putExtra("JobDesc", jobDescription);
        intent.putExtra("JobTitle", jobTitle);
        intent.putExtra("JobSalary", jobSalary);




        //Add shared Animation
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> (button1, "transition_next_btn");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PostJob.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }

    }

    private boolean validateJobDesc() {
        String val = Job_Desc.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Job_Desc.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Job_Desc.setError(null);
            Job_Desc.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateJobContact() {
        String val = Job_Contact.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Job_Contact.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Job_Contact.setError(null);
            Job_Contact.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateJobSalary() {
        String val = Job_Salary.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Job_Salary.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Job_Salary.setError(null);
            Job_Salary.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateJobSkills() {
        String val = Job_Skills.getEditText().getText().toString().trim();
        // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            Job_Skills.setError("Enter valid Job Title");
            return false;
            // } else if (!val.matches(checkspaces)) {
            // phonenumber.setError("No White spaces are allowed!");
            //  return false;
        } else {
            Job_Skills.setError(null);
            Job_Skills.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateJobName(){
        String val = job_Title.getEditText().getText().toString().trim();
       // String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            job_Title.setError("Enter valid Job Title");
            return false;
       // } else if (!val.matches(checkspaces)) {
           // phonenumber.setError("No White spaces are allowed!");
          //  return false;
        } else {
            job_Title.setError(null);
            job_Title.setErrorEnabled(false);
            return true;
        }

    }

}