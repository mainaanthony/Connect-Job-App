package com.connect.connecticutapp.localNews;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;
//import com.finder.newsdata.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EstateFragment extends Fragment {

    RecyclerView recyclerView;
    private EstateNewsAdapter adapter;
    ArrayList<EstateNewsItem> parseItems = new ArrayList<>();
    private RelativeLayout progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View design = inflater.inflate(R.layout.fragment_estate, container, false);
        progressBar = design.findViewById(R.id.progressbar);
        recyclerView = design.findViewById(R.id.idRVNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EstateNewsAdapter(parseItems, getContext());
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
                int numPages = 5;
                for (int k = 0; k < numPages; k++) {
                    String url = "https://businesstoday.co.ke/technologia/page/" + k + "/";

                    Document doc = Jsoup.connect(url).get();
                    Elements data = doc.select("div.td-block-row");
                    int size = data.size();
                    Log.d("doc", "doc: " + doc);
                    Log.d("data", "data: " + data);
                    Log.d("size", "" + size);
                    for (int i = 0; i < size; i++) {
                        String imgUrl = data.select("div.td-module-image")
                                .select("img")
                                .eq(i)
                                .attr("data-img-url");
                        if (imgUrl.isEmpty()) {
                            continue;
                        }
                        String title = data.select("h3.td-module-title")
                                .select("a")
                                .eq(i)
                                .text();

                        String author = data.select("span.td-post-author-name")
                                .select("a")
                                .eq(i)
                                .text();
                        String content = data.select("span.td-post-date")
                                .select("time")
                                .eq(i)
                                .text();
                        String detailUrl = data.select("h3.td-module-title")
                                .select("a")
                                .eq(i)
                                .attr("href");

                        parseItems.add(new EstateNewsItem(imgUrl, title, detailUrl, content, author));
                        Log.d("items", "img: " + imgUrl + " . title: " + title + " . Directory:" + detailUrl + " . Content: " + content);
                    }
                }

            } catch (
                    IOException e) {
                Log.d("TAG:", "Error Message=  " + e.getMessage());
                getFragmentManager().beginTransaction().detach(EstateFragment.this).attach(EstateFragment.this).commit();
            }


            return null;
        }
    }

}
