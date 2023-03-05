package com.connect.connecticutapp.chat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.message.chatMessage;

import java.util.List;

public class messageAdapter extends RecyclerView.Adapter <messageAdapter.myViewHolder>{
    private  List<messageList> messageLists;
    private final Context context;

    public messageAdapter(List<messageList> messageLists, Context context) {
        this.messageLists = messageLists;
        this.context = context;
    }

    @NonNull
    @Override
    public messageAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull messageAdapter.myViewHolder holder, int position) {
        messageList list2 = messageLists.get(position);


        holder.name.setText(list2.getName());
        holder.lastMessage.setText(list2.getLastmessage());

        if(list2.getUnseenMessage() ==0){
            holder.unseenMessage.setVisibility(View.GONE);
            holder.lastMessage.setTextColor(Color.parseColor("#959595"));


        }
        else{
            holder.unseenMessage.setVisibility(View.VISIBLE);
            holder.unseenMessage.setText(list2.getUnseenMessage()+"");
            holder.lastMessage.setTextColor(context.getResources().getColor(R.color.theme_color_80));
        }

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, chatMessage.class);
                intent.putExtra("name",list2.getName());
                intent.putExtra("chat_key" , list2.getChatKey());
                intent.putExtra("mobile", list2.getMobile());

                context.startActivity(intent);

            }
        });


    }
    
    
    public void updateData(List<messageList> messageLists){

        this.messageLists = messageLists;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    static class myViewHolder extends  RecyclerView.ViewHolder {
        private TextView name;
        private TextView lastMessage;
        private TextView unseenMessage;
        private LinearLayout rootLayout;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.messages_name);
            lastMessage = itemView.findViewById(R.id.lastMessages);
            unseenMessage = itemView.findViewById(R.id.unseenMessages);
            rootLayout = itemView.findViewById(R.id.rootLayout);

        }
    }

}
