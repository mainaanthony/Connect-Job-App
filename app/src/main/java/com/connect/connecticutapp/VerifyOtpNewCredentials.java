package com.connect.connecticutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
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

public class VerifyOtpNewCredentials extends AppCompatActivity {

    String codeBySystem;
    PinView pinFromUser;
    String whatToDo,fullName,email,username,password,date,_gender,_phoneNo;
    TextView otpDescriptionText;
    // Button button5;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp_new_credentials);


        pinFromUser = findViewById(R.id.pin_view_forgot_password);

        _phoneNo = getIntent().getStringExtra("phoneNo");
        System.out. println("numba:"+_phoneNo);


        mAuth = FirebaseAuth.getInstance();
        // otpDescriptionText = findViewById(R.id.otp_);

        whatToDo = getIntent().getStringExtra("whatToDo");


        fullName = getIntent().getStringExtra("fullname");
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");

        password = getIntent().getStringExtra("password");
//        System.out.println("gather"+password);
        date = getIntent().getStringExtra("date");
        _gender = getIntent().getStringExtra("gender");
        String PhoneNumber=_phoneNo;

        sendVerificationCodeToUser(PhoneNumber);








    }

    private void sendVerificationCodeToUser(String PhoneNumber) {

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
            Toast.makeText(VerifyOtpNewCredentials.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(VerifyOtpNewCredentials.this, "Verification not Completed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });


    }

    private void storeNewUsersData() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference().child("Users");

        //Create helperclass reference and store data using firebase
        UserHelperClass addNewUser = new UserHelperClass(fullName, username, email,_phoneNo,password,date,_gender);
        reference.child(_phoneNo).setValue(addNewUser);
        //  reference.setValue("First Record");



//
        startActivity(new Intent(getApplicationContext(), com.connect.connecticutapp.dashboard_activity.class));
        finish();
    }

    private void updateOldUsersData() {

        Intent intent = new Intent(getApplicationContext(), setNewPassword.class);
        intent.putExtra("phoneNo", _phoneNo);
        startActivity(intent);
        finish();

    }


    public void callNextScreenFromOtp(View view) {

        String code = pinFromUser.getText().toString();
        if(!code.isEmpty()){
            pinFromUser.setText(code);
            verifyCode(code);
        }



    }


}