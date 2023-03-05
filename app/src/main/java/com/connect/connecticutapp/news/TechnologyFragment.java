package com.connect.connecticutapp.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {

    String api="467a2e23e9d24536bb7a46797e865ef3";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country ="in";
    private RecyclerView recyclerViewoftechnology;
    private String category ="technology";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.healthfragment, null);

        recyclerViewoftechnology =  v.findViewById(R.id.recyvieweroftechnology);
        modelClassArrayList = new ArrayList<>();
//        recyclerViewoftechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); // or vertical whatever you want
        recyclerViewoftechnology.setLayoutManager(layoutManager);
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewoftechnology.setAdapter(adapter);



        findNews();

        return v;

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,100,category, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }








//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       View v = inflater.inflate(R.layout.sciencefragment, null);




//        recyclerViewofscience =  v.findViewById(R.id.recyviewerofscience);
//        modelClassArrayList = new ArrayList<>();
//        recyclerViewofscience.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new Adapter(getContext(), modelClassArrayList);
//        recyclerViewofscience.setAdapter(adapter);
//
//
//
//        findNews();
//
//        return v;
//
//    }
//
//    private void findNews() {
//
//        ApiUtilities.getApiInterface().getCategoryNews(country, category,100,api).enqueue(new Callback<MainNews>() {
//            @Override
//            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
//                if (response.isSuccessful()) {
//                    modelClassArrayList.addAll(response.body().getArticles());
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MainNews> call, Throwable t) {
//
//            }
//        });
//
//
//
    // }
}


