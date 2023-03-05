package com.connect.connecticutapp.jobSeeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.connect.connecticutapp.Articles.articlePerJobSeeker;
import com.connect.connecticutapp.Articles.postArticle;
import com.connect.connecticutapp.PostJob;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.employer.jobsPerEmployer;
import com.connect.connecticutapp.user_profile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class userProfileJobSeeker extends AppCompatActivity {

    private Button button1, button2, button3;
    TextInputLayout jobSeekerName, jobSeekerContact, jobSeekerEmail, jobSeekerPhoneNo, jobSeekerPass;
    String searchID,con_tact,confirm_user, _fullName, job_contact, password,email;
    DatabaseReference reference;
    TextView full_n_ame, title;
    ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_job_seeker);


        button2 = findViewById(R.id.button_post_article);
        button1 = findViewById(R.id.update_user_profile_jobSeeker);
        button3 = findViewById(R.id.view_update_article);



        //get data from loginEmployer
        _fullName = getIntent().getStringExtra("fullName");
//        job_contact = getIntent().getStringExtra("jobContact");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        confirm_user = getIntent().getStringExtra("confirm_user");

        home = findViewById(R.id.home_from_jobseeker);

        full_n_ame = findViewById(R.id.full_name_tony);
        title = findViewById(R.id.title_job_seeker_tony);


        jobSeekerName = findViewById(R.id.full_name_profile_jobSeeker);
        jobSeekerEmail = findViewById(R.id.email_jobSeeker);
        jobSeekerContact = findViewById(R.id.phone_number_jobSeeker);
        jobSeekerPass= findViewById(R.id.password_jobSeeker);
        TextView full_name_profile = findViewById(R.id.full_name_tony);


         full_name_profile.setText(_fullName);
         title.setText(email);
        jobSeekerPass.getEditText().setText(password);
        jobSeekerName.getEditText().setText(_fullName);
        jobSeekerContact.getEditText().setText(confirm_user);
        jobSeekerEmail.getEditText().setText(email);



        //firebase Database
        reference = FirebaseDatabase.getInstance().getReference().child("Employers");






        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent beach = new  Intent(userProfileJobSeeker.this, dashboard_activity.class);
                beach.putExtra("fullName" , _fullName);
                beach.putExtra("phoneNo", confirm_user);
                beach.putExtra("email", email);
                startActivity(beach);
            }
        });


//        button3.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), PostJob.class);
//            startActivity(intent);
//        });



        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), postArticle.class);
            startActivity(intent);
        });


        button3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), articlePerJobSeeker.class);
            intent.putExtra("jobContact",con_tact);
            startActivity(intent);
        });



        con_tact = getIntent().getStringExtra("jobContact");
        confirm_user = getIntent().getStringExtra("confirm_user");
        System.out.println("waziiiiiiii"+con_tact);
        searchID = getIntent().getStringExtra("jobTitle");
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dref = readRef.child("Users");
        Query query = dref.orderByChild("phoneNo").equalTo(confirm_user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {








            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {




//                        EmployerName.setText((ds.child("fullName").getValue().toString()));
//                        EmployerEmail.setText((ds.child("email").getValue().toString()));
//                        employerPass.setText((ds.child("password").getValue().toString()));
//                        EmployerContact.setText((ds.child("phoneNo").getValue().toString()));


                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
            }
        });



    }

    public void UpdateOnClick(View view) {
        if(isNameChanged()||isPasswordChanged ()){

            Toast.makeText(this, "Data has been updated successfully ", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isNameChanged(){
        if(!_fullName.equals(jobSeekerName.getEditText().getText().toString())){
            reference.child(job_contact).child("fullName").setValue(jobSeekerName.getEditText().getText().toString());
            _fullName = jobSeekerName.getEditText().getText().toString();
            return true;




        }else{
            return false;
        }

    }
    private boolean isPasswordChanged(){
        if(!password.equals(jobSeekerPass.getEditText().getText().toString())){
            reference.child(job_contact).child("password").setValue(jobSeekerPass.getEditText().getText().toString());
            password = jobSeekerPass.getEditText().getText().toString();
            return true;




        }else{
            return false;
        }


    }



    }

