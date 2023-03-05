package com.connect.connecticutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class loginJobSeeker extends AppCompatActivity {
private Button button1, button2,forgotPassword;
TextInputLayout phoneNumber, password;
CountryCodePicker countryCodePicker;
RelativeLayout progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_job_seeker);



        button1 = findViewById(R.id.login_job_seeker);
        button2 = findViewById(R.id.create_acc_job_seeker);
        forgotPassword = findViewById(R.id.forgot_password_job_seeker_button);
        phoneNumber = findViewById(R.id.login_phone_number_job_seeker);
        password = findViewById(R.id.login_password_job_seeker);
        countryCodePicker = findViewById(R.id.country_code_picker_login);
        progressbar = findViewById(R.id.progressbar);


        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),  forgetPassword.class);
            startActivity(intent);
        });
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), com.connect.connecticutapp.create_account_job_seeker.class);
            startActivity(intent);
        });

    }

    public void letTheUserLoggedIn(View view) {


        progressbar.setVisibility(View.VISIBLE);

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }

      final   String _completePhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + _phoneNumber;

//Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        
                        password.setError(null);
                        password.setErrorEnabled(false);


                        String _fullname = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneNo = snapshot.child(_completePhoneNumber).child("phoneNO").getValue(String.class);
                        String _dateOfBirth = snapshot.child(_completePhoneNumber).child("date").getValue(String.class);


                        Toast.makeText(loginJobSeeker.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), dashboard_activity.class);
                        intent.putExtra("fullName", _fullname);
                        intent.putExtra("jobContact",phoneNumber.getEditText().getText().toString().trim());
                        intent.putExtra("confirm_user", _completePhoneNumber);
                        intent.putExtra("email", _email);
                        intent.putExtra("password" ,systemPassword);
                        startActivity(intent);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(loginJobSeeker.this, "password does not match", Toast.LENGTH_SHORT).show();

                    }

                }
                else{
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(loginJobSeeker.this, "Data does not exist", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(loginJobSeeker.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}