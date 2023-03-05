package com.connect.connecticutapp.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.create_account_job_seeker;
import com.connect.connecticutapp.create_account_job_seeker_2;
import com.google.android.material.textfield.TextInputLayout;

public class create_account_employer_1 extends AppCompatActivity {
    //variables
    private Button button1;
    //Get Data variables
    TextInputLayout fullName, email, password, phonenumber, organizationName;
    String full;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_employer1);

        button1 = findViewById(R.id.next_to_employer_create_account_2);

        //Hooks for getting data
        organizationName = findViewById(R.id.employer_organisation_name);
        email = findViewById(R.id.employer_signup_email);
        fullName = findViewById(R.id.employer_signup_fullname);
        password = findViewById(R.id.employer_signup_password);
        phonenumber = findViewById(R.id.employer_signup_phonenumber);




    }

    public void callNextSignupScreenemployer(View view) {


        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }
//Get data for all fields

        String full = fullName.getEditText().getText().toString().trim();
        String name = getIntent().getStringExtra("fullname");
        String pass = password.getEditText().getText().toString().trim();
        System.out.println("kumbaff"+pass);
        String Email = email.getEditText().getText().toString().trim();
        String User = organizationName.getEditText().getText().toString().trim();
        String email =getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("organizationName");
        String password = getIntent().getStringExtra("password");





        Intent intent = new Intent(getApplicationContext(), create_account_employer_2.class);

        intent.putExtra("fullname", full);
        System.out.println("wewe: "+full);
        intent.putExtra("email", Email);
        intent.putExtra("organizationName", User);
        intent.putExtra("password", pass);





        //Add shared Animation
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> (button1, "transition_next_btn");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(create_account_employer_1.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }

    }
    //validate functions
    private boolean validateFullName() {
        String full = fullName.getEditText().getText().toString().trim();
        if (full.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = organizationName.getEditText().getText().toString().trim();
        //  String checkspaces = "Aw{1,20}z";

        if (val.isEmpty()) {
            organizationName.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            organizationName.setError("Username is too large!");
            return false;
            //} //else if (!val.matches(checkspaces)) {
            // username.setError("No White spaces are allowed!");
            // return false;
        } else {
            organizationName.setError(null);
            organizationName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                //   ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
            // } else if (!val.matches(checkPassword)) {
            // password.setError("Password should contain 4 characters!");
            //return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhoneNumber() {
        String val = phonenumber.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phonenumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkspaces)) {
            phonenumber.setError("No White spaces are allowed!");
            return false;
        } else {
            phonenumber.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }
    }
}