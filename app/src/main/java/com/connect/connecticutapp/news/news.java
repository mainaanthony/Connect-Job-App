package com.connect.connecticutapp.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.connect.connecticutapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class news extends AppCompatActivity {


    TabLayout tabLayout;
    TabItem mhome, msports, mhealth, mscience,  mentertainment, mtechnology;
//    Toolbar mtollbar;
    PagerAdapter pagerAdapter;


    String api="467a2e23e9d24536bb7a46797e865ef3";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


//   mtollbar = findViewById(R.id.toolbar);
//   setSupportActionBar(mtollbar);




   mhome = findViewById(R.id.hometab);
   msports= findViewById(R.id.sports);
        mhealth= findViewById(R.id.health);
        mscience= findViewById(R.id.science);
        mentertainment= findViewById(R.id.entertainment);
        mtechnology= findViewById(R.id.technology);


        ViewPager viewPager= findViewById(R.id.fragment_container);
        tabLayout=findViewById(R.id.include);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);


         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 viewPager.setCurrentItem(tab.getPosition());
                 if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||tab.getPosition()==3||tab.getPosition()==4||tab.getPosition()==5)
                 {
                     pagerAdapter.notifyDataSetChanged();
                 }
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });

viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}