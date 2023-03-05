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

public class HomeFragment  extends Fragment {

    String api="467a2e23e9d24536bb7a46797e865ef3";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country ="in";
    private  RecyclerView recyclerViewofHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.homenewsfragment, null);

       recyclerViewofHome =  v.findViewById(R.id.recyviewerofhome);
       modelClassArrayList = new ArrayList<>();
       recyclerViewofHome.setLayoutManager(new LinearLayoutManager(getContext()));
       adapter = new Adapter(getContext(), modelClassArrayList);
       recyclerViewofHome.setAdapter(adapter);



       findNews();

        return v;

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(country, 100, api).enqueue(new Callback<MainNews>() {
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
}
