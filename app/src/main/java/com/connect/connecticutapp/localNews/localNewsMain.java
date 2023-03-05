package com.connect.connecticutapp.localNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.news.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class localNewsMain extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mhome;
    localNewsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_news_main);



        mhome = findViewById(R.id.hometab_local);
        ViewPager viewPager= findViewById(R.id.fragment_container_local);
        tabLayout=findViewById(R.id.include_local);

        pagerAdapter = new localNewsPagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
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