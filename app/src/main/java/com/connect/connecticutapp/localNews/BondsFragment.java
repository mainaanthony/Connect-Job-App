package com.connect.connecticutapp.localNews;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.connect.connecticutapp.R;
//import com.finder.newsdata.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class BondsFragment extends Fragment {
    RecyclerView recyclerView;
    private BondNewsAdapter adapter;
    ArrayList<BondNewsItem> parseItems = new ArrayList<>();
    private RelativeLayout progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View design = inflater.inflate(R.layout.fragment_bonds, container, false);
        progressBar = design.findViewById(R.id.progressbar);
        recyclerView = design.findViewById(R.id.idRVNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BondNewsAdapter(parseItems, getContext());
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();
        return design;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://www.wsj.com/news/markets/bonds?mod=nav_left_subsection";

                Document doc = Jsoup.connect(url).get();

                        Elements data = doc.select("main").select("article");
                        int size = data.size();
                        Log.d("doc", "doc: " + doc);
                        Log.d("data", "data: " + data);
                        Log.d("size", "" + size);
                        for (int i = 0; i < size; i++) {
                            String imgUrl = data.select("div.WSJTheme--image--1RvJrX_o")
                                    .select("a")
                                    .select("img")
                                    .eq(i)
                                    .attr("src");
                            if (imgUrl.isEmpty()) {
                                continue;
                            }
                            String title = data.select("div.WSJTheme--content-float-right--1NZyrHNk ")
                                    .select("h2")
                                    .select("a")
                                    .eq(i)
                                    .text();

                            String author = data.select("div.WSJTheme--content-float-right--1NZyrHNk")
                                    .select("p.WSJTheme--byline--1oIUvtQ3")
                                    .eq(i)
                                    .text();
                            String content = data.select("div.WSJTheme--content-float-right--1NZyrHNk")
                                    .select("p.WSJTheme--timestamp--22sfkNDv")
                                    .eq(i)
                                    .text();

                            String detailUrl = data.select("div.WSJTheme--content-float-right--1NZyrHNk")
                                    .select("h2")
                                    .select("a")
                                    .eq(i)
                                    .attr("href");
                            parseItems.add(new BondNewsItem(imgUrl, title, detailUrl, content, author));
                            Log.d("items", "img: " + imgUrl + " . title: " + title + " . Directory:" + detailUrl + " . Content: " + content);
                        }


            } catch (Exception e) {
                Log.d("TAG:", "Error Message=  " + e.getMessage());
                getFragmentManager().beginTransaction().detach(BondsFragment.this).attach(BondsFragment.this).commit();
            }


            return null;
        }
    }

}
