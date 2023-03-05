package com.connect.connecticutapp.employer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.create_account_job_seeker_2;

import java.util.Calendar;

public class create_account_employer_2 extends AppCompatActivity {

    private Button button1, button2;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_employer2);

        //Hooks
        button1 = findViewById(R.id.signup_next_button_job_seeker_to_create_acc_3);
        radioGroup = findViewById(R.id.radio_group_employer);
        datePicker = findViewById(R.id.age_picker_employer);




    }

    public void call3rdSignupScreen(View view) {

        if (!validateGender() | !validateAge()) {
            return;
        }




        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());

        //get data from all fields
        String _gender =selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String _date = day+"/"+month+"/"+year;

        String name = getIntent().getStringExtra("fullname");
        String email = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("organizationName");

        String password = getIntent().getStringExtra("password");
       System.out.println("umbwaa"+password);





        Intent intent = new Intent(getApplicationContext(), create_account_employer_3.class);

        //Pass data to the next activity

        intent.putExtra("fullname", name);
        intent.putExtra("email", email);
        intent.putExtra("organizationName", username);
        intent.putExtra("password", password);
        intent.putExtra("date",_date);
        intent.putExtra("gender",_gender);




        //Add shared Animation
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String> (button1, "transition_next_btn");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(create_account_employer_2.this, pairs);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }




    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}