package com.connect.connecticutapp.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.connect.connecticutapp.MemoryData;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.forgetPassword;
import com.connect.connecticutapp.loginJobSeeker;
import com.connect.connecticutapp.user_profile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class loginEmployer extends AppCompatActivity {
    private Button button1, button2;
    TextInputLayout phoneNumber, password;
    CountryCodePicker countryCodePicker;
    RelativeLayout progressbar;
    String _completePhoneNumber;
    String _phoneNumber;
    Button forgotPasswordEmployer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_employer);



        button1 = findViewById(R.id.login_employer);
        button2 = findViewById(R.id.create_acc_employer);
        phoneNumber = findViewById(R.id.login_phone_number_employer);
        password = findViewById(R.id.login_password_employer);
        forgotPasswordEmployer = findViewById(R.id.forgot_password_button_employer);
        countryCodePicker = findViewById(R.id.country_code_picker_login_employer);
        progressbar = findViewById(R.id.progressbarEmployer);


        forgotPasswordEmployer.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),  forgetPasswordEmployer.class);
            startActivity(intent);
        });

        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), create_account_employer_1.class);
            startActivity(intent);
        });

    }

    public void letTheUserLoggedIn(View view) {


        progressbar.setVisibility(View.VISIBLE);

         _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }

        _completePhoneNumber = "+" + countryCodePicker.getSelectedCountryCode() + _phoneNumber;

//Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Employers").orderByChild("phoneNo").equalTo(_completePhoneNumber);


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
                        String password = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);


                        //save mobile to MemoryData
                        MemoryData.saveData(_completePhoneNumber, loginEmployer.this);


                        //save name to memory
                        MemoryData.saveName(_fullname, loginEmployer.this);
                        
                        
                        Toast.makeText(loginEmployer.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), user_profile.class);
                        intent.putExtra("jobContact",phoneNumber.getEditText().getText().toString().trim());
                        intent.putExtra("confirm_user", _completePhoneNumber);
                        intent.putExtra("fullName", _fullname);
                        intent.putExtra("password", password);
                        intent.putExtra("email", _email);

                        startActivity(intent);
                        finish();

                    }
                    else{
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(loginEmployer.this, "password does not match", Toast.LENGTH_SHORT).show();

                    }

                }
                else{
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(loginEmployer.this, "Data does not exist", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(loginEmployer.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }
}