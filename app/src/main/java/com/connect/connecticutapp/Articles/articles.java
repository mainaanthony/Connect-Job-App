package com.connect.connecticutapp.Articles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.connect.connecticutapp.MyAdapter;
import com.connect.connecticutapp.R;
import com.connect.connecticutapp.dashboard_activity;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.JobsPage;
import com.connect.connecticutapp.model.RecyclerViewInterface;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class articles extends AppCompatActivity implements RecyclerViewInterface {
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    FirebaseAuth mJob;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ArticlesData> list;
    ArticlesAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        drawerLayout = findViewById(R.id.drawer_layout_articles);
//        navigationView = findViewById(R.id.navigationView);
//        menuIcon = findViewById(R.id.menu_icon);
//        contentView = findViewById(R.id.contentView);



        recyclerView = findViewById(R.id.rvArticles);
        mJob = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("Article");

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =new ArticlesAdapterClass(this,list, this);
        recyclerView.setAdapter(adapter);


        Query query = reference.orderByChild("timestamp");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    ArticlesData data = dataSnapshot.getValue(ArticlesData.class);
                    list.add(data);
//                   String ma = snapshot.child("jobTitle").getValue().toString();
//                   System.out.println("The jobs are"+ma);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(articles.this, articlesPage.class);



        intent.putExtra("articleTitle", list.get(position).getArticleTitle());
        intent.putExtra("articleDescription", list.get(position).getArticleDescription());
        intent.putExtra("articleContent", list.get(position).getArticleContent());


        startActivity(intent);
    }
}