package com.connect.connecticutapp.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.VerifyOtp;
import com.connect.connecticutapp.create_account_job_seeker_3;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class create_account_employer_3 extends AppCompatActivity {
    //variables
    ScrollView scrollview;
    Button button1, button2;
    TextInputLayout phonenumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_employer3);

        scrollview = findViewById(R.id.signup_3rd_screen_scroll_view_employer);
        button1 = findViewById(R.id.signup_next_button_job_seeker_To_Otp_employer);
//        button2 = findViewById(R.id.signup_login_button_job_seeker_back);
        phonenumber = findViewById(R.id.signup_phone_number_employer);
        countryCodePicker = findViewById(R.id.country_code_picker_employer);


        button1.setOnClickListener(view -> {
            callVerifyOTPScreen();
        });



    }

    private void callVerifyOTPScreen() {
        //validate fields
        if(!validatePhoneNumber()){
            return;
        }//validation succeeded and now can proceed to next screen to verify phone number and save data

        //Get all data values from previous screens using intent    tools:node="merge"

        String name = getIntent().getStringExtra("fullname");
//        System.out.println("numba:"+name);
        String email = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("organizationName");
        String password = getIntent().getStringExtra("password");
//        System.out.println("pokeaaaa"+password);
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");
//        System.out.println("numba:"+_gender);
        String whatToDO = getIntent().getStringExtra("whatToDO");

        String _getUserEnteredPhoneNumber = phonenumber.getEditText().getText().toString().trim();//get phone number
//        String _phoneNo ="+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;

//Remove first zero if entered!
        if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
//Complete phone number
        final String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _getUserEnteredPhoneNumber;

        Intent intent = new Intent(getApplicationContext(), VerifyOtpEmployer.class);


        //Pass data to the next activity

        intent.putExtra("fullname", name);
        intent.putExtra("email", email);
        intent.putExtra("organizationName", username);
        intent.putExtra("password", password);
        intent.putExtra("date",_date);
        intent.putExtra("gender",_gender);
        intent.putExtra("phoneNo",_phoneNo);
        intent.putExtra("WhatToDo", "createNewUser");// This is to identify that which action should OTP perform after verification.


        //Add shared Animation
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> (scrollview, "transition_OTP_Screen");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(create_account_employer_3.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }





    }

    private boolean validatePhoneNumber() {
        String val = phonenumber.getEditText().getText().toString().trim();
        //String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phonenumber.setError("Enter valid phone number");
            return false;
            //} else if (!val.matches(checkspaces)) {
            //  phonenumber.setError("No White spaces are allowed!");
            // return false;
        } else {
            phonenumber.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }
    }
}