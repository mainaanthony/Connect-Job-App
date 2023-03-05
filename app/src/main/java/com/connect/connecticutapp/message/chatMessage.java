package com.connect.connecticutapp.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.connect.connecticutapp.MemoryData;
import com.connect.connecticutapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class chatMessage extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://connecticutapp-default-rtdb.firebaseio.com/");


    private final List<chatList> chatLists = new ArrayList<>();
    private  String chatKey;
    private chatAdapter chtAdapter;
    String getUserMobile = "";
    private RecyclerView chattingRecyclerView;
    private boolean loadingFirstTime = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        final ImageView backBtn = findViewById(R.id.back_button_chat);
        final ImageView sendBTn = findViewById(R.id.send_btn);
        final TextView nameTv = findViewById(R.id.name_chat_message);
        final EditText editText = findViewById(R.id.messageEditTxt);



       chattingRecyclerView = findViewById(R.id.chattingRecycler);
        //get Data from message Adapter Class

        final String getName = getIntent().getStringExtra("name");
         chatKey = getIntent().getStringExtra("chat_key");
        final String getMobile = getIntent().getStringExtra("mobile");

        //get user mobile from memory
        getUserMobile = MemoryData.getData(chatMessage.this);




        nameTv.setText(getName);

        chattingRecyclerView.setHasFixedSize(true);
        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(chatMessage.this));


        chtAdapter = new chatAdapter(chatLists, chatMessage.this);
        chattingRecyclerView.setAdapter(chtAdapter);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(chatKey.isEmpty()) {
                        //generate chat key which by default is 1

                        chatKey = "1";

                        if (snapshot.hasChild("chat")) {
                            chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
                        }
                    }

                    if (snapshot.hasChild("chat")){


                        if(snapshot.child("child").child(chatKey).hasChild("messages")){

                             chatLists.clear();
                            for(DataSnapshot messagesSnapshot:   snapshot.child("child").child(chatKey).getChildren()){


                                if(messagesSnapshot.hasChild("msg")&&messagesSnapshot.hasChild("mobile")){

                                    final String messagesTimestamp = messagesSnapshot.getKey();
                                    final String getMobile = messagesSnapshot.child("mobile").getValue(String.class);
                                    final String getMsg = messagesSnapshot.child("msg").getValue(String.class);


                                    Timestamp timestamp = new Timestamp(Long.parseLong(messagesTimestamp));
                                    Date date = new Date(timestamp.getTime());
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy ", Locale.getDefault());
                                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

                                    chatList chList = new chatList(getMobile,getName,getMsg,simpleDateFormat.format(date),simpleTimeFormat.format(date));
                                    chatLists.add(chList);

                                    if(loadingFirstTime||Long.parseLong(messagesTimestamp) > Long.parseLong(MemoryData.getLastMsgTS(chatMessage.this, chatKey))){


                                        loadingFirstTime= false;
                                        MemoryData.saveLastMsgTS(messagesTimestamp, chatKey, chatMessage.this);



                                        chtAdapter.updateChatLists(chatLists);

                                        chattingRecyclerView.scrollToPosition(chatLists.size()-1);

                                    }


                                }



                            }
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        sendBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getTxtMessage = editText.getText().toString();



                //get current timeStamps
                final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0,10);






                databaseReference.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
                databaseReference.child("chat").child(chatKey).child("user_2").setValue(getMobile);
                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("msg").setValue(getTxtMessage);
                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("mobile").setValue(getUserMobile);

                //clear edit text
                editText.setText("");


            }
        });

backBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});





    }
}