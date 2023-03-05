package com.connect.connecticutapp.employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.UserHelperClass;
import com.connect.connecticutapp.VerifyOtp;
import com.connect.connecticutapp.setNewPassword;
import com.connect.connecticutapp.user_profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOtpEmployer extends AppCompatActivity {
    String codeBySystem;
    PinView pinFromUser;
    String whatToDo,fullName,email,organizationName ,password,date,_gender,_phoneNo, co_ntact;
    TextView otpDescriptionText;
    // Button button5;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp_employer);


        pinFromUser = findViewById(R.id.pin_view_employer);

        _phoneNo = getIntent().getStringExtra("phoneNo");
        co_ntact = getIntent().getStringExtra("email");
        System.out. println("numba:"+_phoneNo);


        //Remove first zero if entered!
        //  if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
        //     _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        //   }
//Complete phone number
        //   final String _phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;

        mAuth = FirebaseAuth.getInstance();
        // otpDescriptionText = findViewById(R.id.otp_);

        whatToDo = getIntent().getStringExtra("WhatToDo");


        fullName = getIntent().getStringExtra("fullname");
        email = getIntent().getStringExtra("email");
        organizationName = getIntent().getStringExtra("organizationName");

        password = getIntent().getStringExtra("password");
        System.out.println("gather"+password);
        date = getIntent().getStringExtra("date");
        _gender = getIntent().getStringExtra("gender");
        String PhoneNumber=_phoneNo;

        sendVerificationCodeToUser(PhoneNumber);










    }

    private void sendVerificationCodeToUser(String PhoneNumber) {






//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNo, //phone number to verify
//                60, // Timeout duration
//                TimeUnit.SECONDS, //Unit of timeout
//                (Activity) TaskExecutors.MAIN_THREAD, //Activity for callback binding
//                mCallbacks); //

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(PhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

//        firebaseAuth = FirebaseAuth.getInstance();

    }




    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem =s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                pinFromUser.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyOtpEmployer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //  Toast.makeText(VerifyOtp.this, "Verification  Completed", Toast.LENGTH_SHORT).show();
                            //Verification completed successfully here Either
                            // store the data or do whatever desire
                            if (whatToDo.equals("updateData")) {
                                updateOldUsersData();
                            } else if (whatToDo.equals("createNewUser")) {
                                storeNewUsersData();
                            }



                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyOtpEmployer.this, "Verification not Completed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    private void storeNewUsersData() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference().child("Employers");

        //Create helperclass reference and store data using firebase
        EmployerJavaClass addNewUser = new EmployerJavaClass(fullName, organizationName, email,_phoneNo,password,date,_gender);
        reference.child(_phoneNo).setValue(addNewUser);
        //  reference.setValue("First Record");



//
//        startActivity(new Intent(getApplicationContext(), user_profile.class));//check if system crashes and apply to the other related activities
        Intent intent = new Intent(getApplicationContext(), user_profile.class);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("jobContact", co_ntact);
        startActivity(intent);
        finish();
        finish();

    }
    private void updateOldUsersData() {
    }

    public void callNextScreenFromOtp(View view) {

        //  storeNewUsersData();

        String code = pinFromUser.getText().toString();
        if (!code.isEmpty()) {
            pinFromUser.setText(code);
            verifyCode(code);
        }


    }
}