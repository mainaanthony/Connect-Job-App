package com.connect.connecticutapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final int SPLASH_SCREEN = 5000;


    //variables
    Animation topAnim, bottomAnim;
    ImageView Image;
    android.widget.TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        Image = findViewById(R.id.imageView);
        logo = findViewById(R.id.Connect_starter);
        slogan = findViewById(R.id.Slogan);


     new Handler(). postDelayed((Runnable) () -> {
         Intent intent = new Intent(MainActivity.this, Dashboard.class);
         startActivity(intent);
         finish();
     }, SPLASH_SCREEN);

    }
}

