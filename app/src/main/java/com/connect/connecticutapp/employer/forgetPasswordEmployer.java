package com.connect.connecticutapp.employer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.VerifyOtpNewCredentials;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class forgetPasswordEmployer extends AppCompatActivity {

    TextInputLayout phoneNumber;
    TextView title, description;
    CountryCodePicker countryCodePicker;
    Button buttonForget;
    private Animation animation;
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_employer);


//  Hooks
        phoneNumber = findViewById(R.id.phone_number_forgot_password);
        title = findViewById(R.id.forgot_password_title);
        description = findViewById(R.id.forgot_password_details);
        countryCodePicker = findViewById(R.id.country_code_picker_forgot_password);
        buttonForget = findViewById(R.id.forget_password_next_button);
        progressbar = findViewById(R.id.progressbarForgetPassword);

        //animation = AnimationUtils.LoadAnimation(this, R.anim.slide_animation);
        //Animations

      //  title.setAnimation(animation);



        String whatToDO = getIntent().getStringExtra("whatToDO");


    }

    public void verifyPhoneNumber(View view) {
        //check internet connection
        //CheckInternet checkInternet = new CheckInternet
        //if(!checkInternet.isConnected(this)){}


        //validate phoneNumber
        // if(!validateFields()){}
        //progressbar.setVisibility(View.Visible);


        //get data from fields
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        if(_phoneNumber.charAt(0) =='0'){
            _phoneNumber = _phoneNumber.substring(1);
        }
     final String _completePhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + _phoneNumber;


        //check whether the user exists in the database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Employers").orderByChild("phoneNo").equalTo(_completePhoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);


                    Intent intent = new Intent(getApplicationContext(), VerifyOtpNewCredentialsEmployer.class);
                    intent.putExtra("phoneNo", _completePhoneNumber);
                    intent.putExtra("whatToDo","updateData");
                    startActivity(intent);
                    finish();

                }else{

                    progressbar.setVisibility(View.GONE);
                    phoneNumber.setError("No such user exists");
                    phoneNumber.requestFocus();






//                    String systemPassword = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);
//                    if (systemPassword.equals(_password)) {
//
//                        password.setError(null);
//                        password.setErrorEnabled(false);
//
//
//                        String _fullname = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
//                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
//                        String _phoneNo = snapshot.child(_completePhoneNumber).child("phoneNO").getValue(String.class);
//                        String _dateOfBirth = snapshot.child(_completePhoneNumber).child("date").getValue(String.class);
//
//
//                        Toast.makeText(forgetPassword.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth, Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), dashboard_activity.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else{
//                        progressbar.setVisibility(View.GONE);
//                        Toast.makeText(forgetPassword.this, "password does not match", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//                else{
//                    progressbar.setVisibility(View.GONE);
//                    Toast.makeText(forgetPassword.this, "Data does not exist", Toast.LENGTH_SHORT).show();
//
//                }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}