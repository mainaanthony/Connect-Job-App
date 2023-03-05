package com.connect.connecticutapp;

import android.content.ClipData;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.Articles.ArticlesData;
import com.connect.connecticutapp.Articles.articles;
import com.connect.connecticutapp.Articles.postArticle;
import com.connect.connecticutapp.chat.chatMainActivity;
import com.connect.connecticutapp.jobSeeker.userProfileJobSeeker;
import com.connect.connecticutapp.localNews.NewsFragment;
import com.connect.connecticutapp.localNews.localNewsMain;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.JobsPage;
import com.connect.connecticutapp.model.RecyclerViewInterface;
import com.connect.connecticutapp.news.news;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class dashboard_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewInterface {
    NavigationView navigationView;
    ImageView menuIcon, messageIcon;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    FirebaseAuth mJob;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Data> list;
    MyAdapter adapter;
    String full, art_contact, Expirydate;
    //Data to jobSeeker initialization
    String searchID,con_tact,confirm_user, _fullName, job_contact, password,email;
    TextView heloo;


    private SearchView searchView;

    static final float END_SCALE = 0.7f;


//    private String countdownStart(String dateformat) {
//
//
//
//
//
////        private String getDate(lon  g time) {
//        SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");
//
//        try {
//            Date date1 = dates.parse(Expirydate);
//
//            Date date2 = dates.parse(dateformat);
//
//
//            long difference = Math.abs(date1.getTime() - date2.getTime());
//
//
//            long differenceDates = difference / (24 * 60 * 60 * 1000);
//
//
//            String dayDifference = Long.toString(differenceDates);
//            System.out.println("mashidaa"+dayDifference);
//
////            jobExpiry.setText("The difference between two dates is"  +dayDifference+"days");
//
//
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
////        handler = new Handler();
////        runnable = new Runnable() {
////            @Override
////            public void run() {
////                handler.postDelayed(this, 10);
////
////                try {
////
////                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////                    TextView jobExpiry = findViewById(R.id.expiry);
////                    jobExpiry.setText(String.valueOf(date));
////                    Date futureDate = dateFormat.parse(date);
////
////
////                    Date currentDate = new Date();
////
////                    if (!currentDate.after(futureDate)) {
////                        long diff = futureDate.getTime() - currentDate.getTime();
////                        long days = diff / (24 * 60 * 60 * 1000);
////                        diff -= days * (24 * 60 * 60 * 1000);
////
////                        jobExpiry.setText("answer");
////
////
////                    } else {
////                        //jobExpiry = findViewById(R.id.expiry);
////                        jobExpiry.setText("00");
////
////                    }
////
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        };
////        handler.postDelayed(runnable, 1 * 10);
//
//        return dateformat;
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        menuIcon = findViewById(R.id.menu_icon);
//        messageIcon = findViewById(R.id.message_icon);
        heloo = findViewById(R.id.helooooooooo);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        contentView = findViewById(R.id.contentView);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new com.connect.connecticutapp.HomeFragment());

        recyclerView = findViewById(R.id.rv1);
        mJob = FirebaseAuth.getInstance();




        //get data from loginJobSeeker to profile jobSeeker
        _fullName = getIntent().getStringExtra("fullName");
        job_contact = getIntent().getStringExtra("jobContact");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        confirm_user = getIntent().getStringExtra("confirm_user");




        full = getIntent().getStringExtra("fullName");
        System.out.println("chelsea" + full);

        art_contact = getIntent().getStringExtra("jobSeekerContact");
//        FirebaseUser mUser = mJob.getCurrentUser();
//        String uId = mUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("JobPost");

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        heloo.setText(_fullName);


        Expirydate = getIntent().getStringExtra("date");
//timestamp issues
//        long timestamp = System.currentTimeMillis();
//
//
//
//        //        convert timestamp into dd/MM/yyyy
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(Long.parseLong(String.valueOf(timestamp)));
//        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();
//
//        System.out.println("ytnnnnv"+dateFormat);
//
//
//
//
//        countdownStart(dateFormat);

//        myImageButton = (ImageButton) findViewById(R.id.imageButton);

//        messageIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent beach = new  Intent(dashboard_activity.this, chatMainActivity.class);
//                beach.putExtra("fullName" , _fullName);
//                beach.putExtra("phoneNo", confirm_user);
//                beach.putExtra("email", email);
//                startActivity(beach);
//            }
//        });

        //searchView Implementation

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterList(newText);
                return true;
            }
        });



        Query query = reference.orderByChild("timestamp");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    String futureDate = dataSnapshot.child("date").getValue(String.class);

                    long timestamp = System.currentTimeMillis();


                    //        convert timestamp into dd/MM/yyyy
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(Long.parseLong(String.valueOf(timestamp)));
                    String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();

                    System.out.println("ytnnnnv" + dateFormat);
                    System.out.println("miondokoo"+ futureDate);


                    SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        Date date1 = dates.parse(futureDate);

                        Date date2 = dates.parse(dateFormat);


                        long difference = Math.abs(date1.getTime() - date2.getTime());


                        long differenceDates = difference / (24 * 60 * 60 * 1000);


                        String dayDifference = Long.toString(differenceDates);
                        System.out.println("mashidaa" + dayDifference);

                        if (Integer.parseInt(dayDifference) == 0) {


                            continue;
                        }

                        Data data = dataSnapshot.getValue(Data.class);
                        list.add(data);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


//                   String ma = snapshot.child("jobTitle").getValue().toString();
//                   System.out.println("The jobs are"+ma);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        loadmenu();


        navigationDrawer();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new com.connect.connecticutapp.HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);


        }


//        messageIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent class will help to go to next activity using
//                // it's object named intent.
//                // SecondActivty is the name of new created EmptyActivity.
//                Intent intent = new Intent(dashboard_activity.this, chatMainActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    ///also  implementation of searchView
    private void filterList(String newText) {

        ArrayList<Data> filteredList = new ArrayList<>();
        for (Data data : list) {

            if (data.getJobTitle().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(data);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT);
        } else {
            adapter.setFilteredList(filteredList);
        }

    }


//    private void loadmenu() {
//        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
//                .setQuery(reference, Data.class)
//                .build();
//
//
//        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Data, myViewHolder>
//                (options) {
//            @Override
//            protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Data model) {
//                holder.setJobTitle(model.getJobTitle());
//                holder.setJobContact(model.getJobContact());
//                holder.setJobSalary(model.getJobSalary());
//                holder.setJobSkills(model.getJobSkills());
//                holder.setJobDescription(model.getJobDesc());
//                holder.setJobId(model.getId());
//                holder.setJobDate(model.getDate());
//
//
//
//
//            }
//
//            @NonNull
//            @Override
//            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs, parent, false);
//                return new myViewHolder(view);
//            }
//        };
//
//        recyclerView.setAdapter(adapter);
//    }
//
//
//    static class myViewHolder extends RecyclerView.ViewHolder{
//
//        View myview;
//
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//            myview=itemView;
//
//
//        }
//        public void setJobTitle(String jobTitle){
//            TextView mTitle =myview.findViewById(R.id.post_title);
//            mTitle.setText(jobTitle);
//
//        }
//
//        public void setJobContact(String jobContact){
//            TextView mTitle =myview.findViewById(R.id.post_contact);
//            mTitle.setText(jobContact);
//
//        }
//        public void setJobSkills(String jobSkills){
//            TextView mTitle =myview.findViewById(R.id.post_skills);
//            mTitle.setText(jobSkills);
//
//        }
//        public void setJobSalary(String jobSalary){
//            TextView mTitle =myview.findViewById(R.id.post_salary);
//            mTitle.setText(jobSalary);
//
//        }
//        public void setJobDescription(String jobDesc){
//            TextView mTitle =myview.findViewById(R.id.post_description);
//            mTitle.setText(jobDesc);
//
//        }
//        public void setJobId(String id){
//            TextView mTitle =myview.findViewById(R.id.post_id);
//            mTitle.setText(id);
//
//        }
//        public void setJobDate(String date){
//            TextView mTitle =myview.findViewById(R.id.post_date);
//            mTitle.setText(date);
//
//        }
//
//    }
//
//
//
//
//
//


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new com.connect.connecticutapp.HomeFragment()).commit();
                break;
            case R.id.nav_profile_fragment:
                Intent intent2 = new Intent(this, userProfileJobSeeker.class);
                intent2.putExtra("fullName", _fullName);
//                intent2.putExtra("jobContact",phoneNumber.getEditText().getText().toString().trim());
                intent2.putExtra("confirm_user", confirm_user);
                intent2.putExtra("email", email);
                intent2.putExtra("password" ,password);
                startActivity(intent2);
                break;
            case R.id.nav_articles_fragment:
                Intent intent3 = new Intent(this, postArticle.class);
                startActivity(intent3);

                break;
            case R.id.CIEW_ARTICLES:
                Intent intent = new Intent(this, articles.class);
                startActivity(intent);
                break;

            case R.id.nav_settings_fragment:
                Intent intent1 = new Intent(this, news.class);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                Intent intent5 = new Intent(this, Dashboard.class);
                startActivity(intent5);
                break;

            case R.id.Local_news:
                Intent intent7 = new Intent(this, localNewsMain.class);
                startActivity(intent7);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit();
                break;




        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();


    }

    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                {

                    //scale the view based on current slide offset
                    final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                    final float offsetScale = 1 - diffScaledOffset;
                    contentView.setScaleX(offsetScale);
                    contentView.setScaleY(offsetScale);

                    //Translate the View, accounting for the scaled width
                    final float xOffset = drawerView.getWidth() * slideOffset;
                    final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                    final float xTranslation = xOffset - xOffsetDiff;
                    contentView.setTranslationX(xTranslation);

                }
            }
        });

    }

    public void onBackPressed() {


        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else
            super.onBackPressed();
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(dashboard_activity.this, JobsPage.class);


        intent.putExtra("jobTitle", list.get(position).getJobTitle());
        intent.putExtra("jobContact", list.get(position).getJobContact());
        intent.putExtra("jobSalary", list.get(position).getJobSalary());
        intent.putExtra("date", list.get(position).getDate());
        intent.putExtra("timestamp",list.get(position).getTimestamp());
        System.out.print("wwwwwwwwwwwwwwww  " + list.get(position).getDate());
//        intent.putExtra("JobSkills", list.get(position).getJobSkills());
//        intent.putExtra("JobDescription", list.get(position).getJobDescription());
        intent.putExtra("jobWebsite", list.get(position).getJobWebsite());

        intent.putExtra("fullName", full);
        startActivity(intent);


    }
}