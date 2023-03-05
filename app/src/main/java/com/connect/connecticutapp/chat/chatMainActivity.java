package com.connect.connecticutapp.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.connect.connecticutapp.MemoryData;
import com.connect.connecticutapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class chatMainActivity extends AppCompatActivity {

    private final List<messageList> msgLists = new ArrayList<>();
    private String name, mobile, email;
    private RecyclerView messageRecyclerView;
    private messageAdapter adapter;

    private String chatKey = "";
    private int unseenMessage = 0;
    private String lastMessage = "";
    private boolean dataSet = false;




    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://connecticutapp-default-rtdb.firebaseio.com/");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);


        messageRecyclerView = findViewById(R.id.messageRecyclerView);
         name = getIntent().getStringExtra("fullName");
         mobile = getIntent().getStringExtra("phoneNo");
         email = getIntent().getStringExtra("email");




        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter to recyclerView
        adapter = new messageAdapter(msgLists, chatMainActivity.this);

        messageRecyclerView.setAdapter(adapter);




        databaseReference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                msgLists.clear();
                unseenMessage = 0;
                lastMessage = "";
                chatKey = "";

                for(DataSnapshot dataSnapshot: snapshot.child("Users").getChildren()){


                    final String getMobile = dataSnapshot.getKey();

                    dataSet = false;

                    if(!getMobile.equals(mobile)){

                        final String getName = dataSnapshot.child("fullName").getValue(String.class);





                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                int getChatCounts = (int)snapshot.getChildrenCount();

                                if(getChatCounts>0)  {
                                    
                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren())
                                    {
                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;


                                        if(dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages") ){
                                            final String getUser1 = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUser2 = dataSnapshot1.child("user_2").getValue(String.class);


                                            if((getUser1.equals(getMobile)  && getUser2.equals(mobile)) || (getUser1.equals(mobile)  && getUser2.equals(getMobile))){


                                                for(DataSnapshot chatDataSnapshot : dataSnapshot1.child("messages").getChildren()){


                                                    final long getMessageKey = Long.parseLong(chatDataSnapshot.getKey());
                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTS(chatMainActivity.this, getKey));


                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);

                                                    if(getMessageKey > getLastSeenMessage){

                                                        unseenMessage++;


                                        }




                                                }

                                            }


                                    }


                                    }
                                }

                                if(!dataSet){
                                    dataSet = true;
                                    messageList msgList = new messageList(getName, getMobile, lastMessage,unseenMessage, chatKey);
                                    msgLists.add(msgList);
                                    adapter.updateData(msgLists);
                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));



    }
}