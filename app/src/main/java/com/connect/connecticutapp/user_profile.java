package com.connect.connecticutapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.connect.connecticutapp.chat.chatMainActivity;
import com.connect.connecticutapp.employer.create_account_employer_1;
//import com.connect.connecticutapp.employer.jobsPerEmployer;
import com.connect.connecticutapp.employer.jobsPerEmployer;
import com.connect.connecticutapp.jobPerEmployer.jobPerEmployerMain;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {
    private Button button1, button2, button3;
//    TextView EmployerName, EmployerContact, EmployerEmail,  employerPass;
    String searchID,con_tact,confirm_user, _fullName, job_contact, password, email;
    DatabaseReference reference;
    TextInputLayout EmployerName , pass, phoneNumber, Email;
    ImageView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        button2 = findViewById(R.id.button_post_a_job);
        button1 = findViewById(R.id.update_user_profile);
        button3 = findViewById(R.id.view_update_job);



        //get data from loginEmployer
        _fullName = getIntent().getStringExtra("fullName");
        job_contact = getIntent().getStringExtra("jobContact");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        confirm_user = getIntent().getStringExtra("confirm_user");

       EmployerName = findViewById(R.id.full_name_profile_employer);
       Email = findViewById(R.id.email_employer);
       phoneNumber = findViewById(R.id.phone_number_employer);
       pass = findViewById(R.id.password_employer);
      TextView profile_full_name = findViewById(R.id.full_name_maina);
      TextView title = findViewById(R.id.title_maina);
       messages = findViewById(R.id.Home_from_Employer);


       profile_full_name.setText(_fullName);
       title.setText(email);
       pass.getEditText().setText(password);
       EmployerName.getEditText().setText(_fullName);
       phoneNumber.getEditText().setText(confirm_user);
       Email.getEditText().setText(email);





       //firebase Database
        reference = FirebaseDatabase.getInstance().getReference().child("Employers");





        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent beach = new  Intent(user_profile.this, dashboard_activity.class);
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
            Intent intent = new Intent(getApplicationContext(), PostJob.class);
            startActivity(intent);
        });


        button3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), jobPerEmployerMain.class);
            intent.putExtra("jobContact",con_tact);
            startActivity(intent);
        });



        con_tact = getIntent().getStringExtra("jobContact");
        confirm_user = getIntent().getStringExtra("confirm_user");
        System.out.println("waziiiiiiii"+con_tact);
        searchID = getIntent().getStringExtra("jobTitle");
//        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference dref = readRef.child("Employers");
//        Query query = dref.orderByChild("phoneNo").equalTo(confirm_user);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()) {
//                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                        EmployerName.setText((ds.child("fullName").getValue().toString()));
//                        Email.setText((ds.child("email").getValue().toString()));
//                        pass.setText((ds.child("password").getValue().toString()));
//                        phoneNumber.setText(String.valueOf("you Text")).getValue().toString()));
//
//
//                    }
//                }
//                else
//                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.d("TAG", databaseError.getMessage()); //Don't ignore potential errors!
//            }
//        });



//        progressbar.setVisibility(View.VISIBLE);
//
////        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
////        String _password = password.getEditText().getText().toString().trim();
//
//
//        String name = getIntent().getStringExtra("jobTitle");
//        String contact = getIntent().getStringExtra("jobContact");
//        System.out.println("seleee"+contact);
//
//
//
////        if (_phoneNumber.charAt(0) == '0') {
////            _phoneNumber = _phoneNumber.substring(1);
////        }
////
////        final   String _completePhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + _phoneNumber;
//
////Database
//        Query checkUser = FirebaseDatabase.getInstance().getReference("JobPost").orderByChild("jobTitle").equalTo(name);
//
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
////                    phoneNumber.setError(null);
////                    phoneNumber.setErrorEnabled(false);
//                    String email = snapshot.child(name).child("jobTitle").getValue(String.class);
//                    System.out.println("workkk");
//                    Toast.makeText(JobsPage.this, email, Toast.LENGTH_SHORT).show();
//
//                    String systemPassword = snapshot.child(name).child("jobContact").getValue(String.class);
//                    if (systemPassword != contact) {
//
////                        password.setError(null);
////                        password.setErrorEnabled(false);
//
//
//                        String _fullname = snapshot.child(name).child("jobContact").getValue(String.class);
//                        String _email = snapshot.child(name).child("jobTitle").getValue(String.class);
//                        String _phoneNo = snapshot.child(name).child("jobDescription").getValue(String.class);
//                        String _dateOfBirth = snapshot.child(name).child("jobSkills").getValue(String.class);
//
//
////                        Toast.makeText(JobsPage.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth, Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(getApplicationContext(), dashboard_activity.class);
////                        startActivity(intent);
////                        finish();
//
//                    }
//                    else{
//                        progressbar.setVisibility(View.GONE);
//                        Toast.makeText(JobsPage.this, "password does not match", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//                else{
//                    progressbar.setVisibility(View.GONE);
//                    Toast.makeText(JobsPage.this, "Data does not exist", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                progressbar.setVisibility(View.GONE);
//                Toast.makeText(JobsPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });










    }

    public void UpdateOnClick(View view) {


        if(isNameChanged()||isPasswordChanged ()){

            Toast.makeText(this, "Data has been updated successfully ", Toast.LENGTH_SHORT).show();

        }
    }

     private boolean isNameChanged(){
        if(!_fullName.equals(EmployerName.getEditText().getText().toString())){
            reference.child(job_contact).child("fullName").setValue(EmployerName.getEditText().getText().toString());
            _fullName = EmployerName.getEditText().getText().toString();
                    return true;




        }else{
            return false;
        }

    }
    private boolean isPasswordChanged(){
        if(!password.equals(pass.getEditText().getText().toString())){
            reference.child(job_contact).child("password").setValue(pass.getEditText().getText().toString());
            password = pass.getEditText().getText().toString();
            return true;




        }else{
            return false;
        }


    }
}