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

//import com.finder.newsdata.R;

import com.connect.connecticutapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class StockFragment extends Fragment {
    RecyclerView recyclerView;
    private StockNewsAdapter adapter;
    ArrayList<StockNewsItem> parseItems = new ArrayList<>();
    private RelativeLayout progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View design = inflater.inflate(R.layout.fragment_stock, container, false);
        progressBar = design.findViewById(R.id.progressbar);
        recyclerView = design.findViewById(R.id.idRVNews);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StockNewsAdapter(parseItems, getContext());
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
                String url = "https://www.african-markets.com/en/stock-markets/nse?start=16";

                Document doi = Jsoup.connect(url).get();
               Elements row=doi.select("div.pagination li");
               for (Element e1:row) {
                   String pagination = e1.select("a").attr("href");
                   System.out.println("------------------------------------------------");
                   Log.d("Pagination:", ". fuata: " + pagination);
                   System.out.println("------------------------------------------------");
                   Document doc = Jsoup.connect("https://www.african-markets.com"+pagination).get();
                   Elements ven = doc.select("body");
                   int quantity = ven.size();
                   for (int k = 0; k < quantity; k++) {

                       Elements data = doc.select("div.span6");
                       int size = data.size();
                       Log.d("doc", "doc: " + doc);
                       Log.d("data", "data: " + data);
                       Log.d("size", "" + size);
                       for (int i = 0; i < size; i++) {
                           String img = data.select("div.item-image")
                                   .select("a")
                                   .select("img")
                                   .eq(i)
                                   .attr("data-original");
                           String imgUrl = "https://www.african-markets.com" + img;
                           if (img.isEmpty()) {
                               continue;
                           }
                           String title = data.select("div.page-header")
                                   .select("h2")
                                   .select("a")
                                   .eq(i)
                                   .text();

                           String author = data.select("dl.article-info")
                                   .select("dd.createdby")
                                   .eq(i)
                                   .text();
                           String conten = data.select("dl.article-info")
                                   .select("dd.published")
                                   .select("time")
                                   .eq(i)
                                   .text();
                           String content = conten.replace("Published:", "");

                           String detail = data
                                   .select("div.page-header")
                                   .select("h2")
                                   .select("a")
                                   .eq(i)
                                   .attr("href");

                           String detailUrl = "https://www.african-markets.com" + detail;
                           parseItems.add(new StockNewsItem(imgUrl, title, detailUrl, content, author));
                           Log.d("items", "img: " + imgUrl + " . title: " + title + " . Directory:" + detailUrl + " . Content: " + content);
                       }
                   }
               }

            } catch (IOException e) {
                Log.d("TAG:", "Error Message=  " + e.getMessage());
                getFragmentManager().beginTransaction().detach(StockFragment.this).attach(StockFragment.this).commit();
            }


            return null;
        }
    }

}
