package com.connect.connecticutapp.localNews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.connect.connecticutapp.news.EntertainmentFragment;
import com.connect.connecticutapp.news.HealthFragment;
import com.connect.connecticutapp.news.HomeFragment;
import com.connect.connecticutapp.news.ScienceFragment;
import com.connect.connecticutapp.news.SportsFragment;
import com.connect.connecticutapp.news.TechnologyFragment;

public class localNewsPagerAdapter extends FragmentPagerAdapter {

    int tabcount;

    public localNewsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
            return new NewsFragment();

//            case 1:
//                return new SportsFragment();
//            case 2:
//                return new HealthFragment();
//            case 3:
//                return new ScienceFragment();
//            case 4:
//                return new EntertainmentFragment();
//            case 5:
//                return new TechnologyFragment();




default:
    return null;


        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
