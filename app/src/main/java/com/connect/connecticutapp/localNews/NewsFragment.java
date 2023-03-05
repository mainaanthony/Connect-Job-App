package com.connect.connecticutapp.localNews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.connect.connecticutapp.R;
//import com.finder.newsdata.R;
import com.google.android.material.button.MaterialButton;


public class NewsFragment extends Fragment implements View.OnClickListener {
    MaterialButton stock, estate, bonds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        stock = view.findViewById(R.id.newsStockbt);
        estate = view.findViewById(R.id.newsestatebt);
        bonds = view.findViewById(R.id.newsbondbt);
        stock.setOnClickListener(this);
        estate.setOnClickListener(this);
        bonds.setOnClickListener(this);

        bonds.setBackgroundColor(getResources().getColor(R.color.white));
        bonds.setTextColor(getResources().getColor(R.color.purple_500));
        stock.setBackgroundColor(getResources().getColor(R.color.white));
        stock.setTextColor(getResources().getColor(R.color.purple_500));
        estate.setBackgroundColor(getResources().getColor(R.color.purple_500));
        estate.setTextColor(getResources().getColor(R.color.white));
        Fragment frag;
        frag=new EstateFragment();
        replaceFragment(frag);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View click) {
        Fragment fragment;
        switch (click.getId()){
            case R.id.newsestatebt:
                estate.setBackgroundColor(getResources().getColor(R.color.purple_500));
                estate.setTextColor(getResources().getColor(R.color.white));
                bonds.setBackgroundColor(getResources().getColor(R.color.white));
                bonds.setTextColor(getResources().getColor(R.color.purple_500));
                stock.setBackgroundColor(getResources().getColor(R.color.white));
                stock.setTextColor(getResources().getColor(R.color.purple_500));
                fragment=new EstateFragment();
                replaceFragment(fragment);
                break;
            case R.id.newsbondbt:
                bonds.setBackgroundColor(getResources().getColor(R.color.purple_500));
                bonds.setTextColor(getResources().getColor(R.color.white));
                estate.setBackgroundColor(getResources().getColor(R.color.white));
                estate.setTextColor(getResources().getColor(R.color.purple_500));
                stock.setBackgroundColor(getResources().getColor(R.color.white));
                stock.setTextColor(getResources().getColor(R.color.purple_500));
                fragment=new BondsFragment();
                replaceFragment(fragment);
                break;
            case R.id.newsStockbt:
            default:
                bonds.setBackgroundColor(getResources().getColor(R.color.white));
                bonds.setTextColor(getResources().getColor(R.color.purple_500));
                estate.setBackgroundColor(getResources().getColor(R.color.white));
                estate.setTextColor(getResources().getColor(R.color.purple_500));
                stock.setBackgroundColor(getResources().getColor(R.color.purple_500));
                stock.setTextColor(getResources().getColor(R.color.white));
                fragment=new StockFragment();
                replaceFragment(fragment);
                break;

        }

    }

    @SuppressWarnings("deprecation")
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.news_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}