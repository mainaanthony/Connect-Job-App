package com.connect.connecticutapp.localNews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.connect.connecticutapp.R;
//import com.finder.newsdata.Backend.InternetDialog;
//import com.finder.newsdata.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NewsStockDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout progressbar, newsview;
    TextView titleTExtView, detailTextView, contTextView, authorTextView, dateTextView;
    private String detailString;
//    InternetDialog networkListener = new InternetDialog();
    String description, des1, des2, des3, des4, des5, des6, des7, des8,des9,des10,des11,des12,des13,des14,des15,des16,des17,des18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_stock_details);
        imageView = findViewById(R.id.imageView);
        titleTExtView = findViewById(R.id.textView);
        contTextView = findViewById(R.id.contentTextView);
        detailTextView = findViewById(R.id.detailTextView);
        progressbar = findViewById(R.id.progressbar);
        authorTextView = findViewById(R.id.infoTextView);
        dateTextView = findViewById(R.id.inforTextView);
        newsview = findViewById(R.id.newsdetails);
        Content content = new Content();
        content.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("deprecation")
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detailTextView.setText(detailString);
            authorTextView.setText(getIntent().getStringExtra("Author"));
            titleTExtView.setText(getIntent().getStringExtra("title"));
            dateTextView.setText(getIntent().getStringExtra("Date"));
            Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
            progressbar.setVisibility(View.GONE);
            newsview.setVisibility(View.VISIBLE);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = getIntent().getStringExtra("detailUrl");


                Document doc = Jsoup.connect(url).get();
                Elements details = doc.select("main#jm-maincontent").select("div.item-page").select("p");
                description = details.get(0).text();
                des1 = details.get(1).text();
                des2 = details.get(2).text();
                des3 = details.get(3).text();
                des4 = details.get(4).text();
                des5 = details.get(5).text();
                des6 = details.get(6).text();
                des7 = details.get(7).text();
                des8 = details.get(8).text();
                des9 = details.get(9).text();
                des10 = details.get(10).text();
                des11 = details.get(11).text();
                des12 = details.get(12).text();
                des13 = details.get(13).text();
                des14 = details.get(14).text();
                des15 = details.get(15).text();
                des16 = details.get(16).text();
                des17 = details.get(17).text();
                des18 = details.get(18).text();
                contTextView.setText(description + "\n" + des1 + "\n" + des2 + "\n" + des3 + "\n" + des4 + "\n" + des5 + "\n" + des6 + "\n" + des7 + "\n" + des8 + "\n" + des9 + "\n" + des10 + "\n" + des11 + "\n" + des12 + "\n" + des13 + "\n" + des14 + "\n" + des15 + "\n" + des16 + "\n" + des17+"\n"+des18);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }

//    protected void onStart() {
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkListener, filter);
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        unregisterReceiver(networkListener);
//        super.onStop();
//    }
}