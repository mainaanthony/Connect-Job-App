package com.connect.connecticutapp.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.MemoryData;
import com.connect.connecticutapp.R;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.myViewHolder> {

    private    List<chatList> chatLists;
    private final Context context;
    private String userMobile;

    public chatAdapter(List<chatList> chatLists, Context context) {
        this.chatLists = chatLists;
        this.context = context;
        this.userMobile = MemoryData.getData(context);
    }

    @NonNull
    @Override
    public chatAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull chatAdapter.myViewHolder holder, int position) {
        chatList list2 = chatLists.get(position);

        if(list2.getMobile().equals(userMobile)){


        }else{


            holder.myLayout.setVisibility(View.GONE);
            holder.oppoLayout.setVisibility(View.VISIBLE);

            holder.myMessage.setText(list2.getMessage());
            holder.myTime.setText(list2.getDate()+""+list2.getTime());
        }

        holder.myLayout.setVisibility(View.VISIBLE);
        holder.oppoLayout.setVisibility(View.GONE);

        holder.oppoMessage.setText(list2.getMessage());
        holder.oppoTime.setText(list2.getDate()+""+list2.getTime());
    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public void updateChatLists(List<chatList> chatLists){
        this.chatLists = chatLists;

    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout oppoLayout, myLayout;
        private TextView oppoMessage, myMessage;
        private TextView oppoTime, myTime;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            oppoLayout = itemView.findViewById(R.id.oppoLayout);
            myLayout = itemView.findViewById(R.id.myLayout);
            oppoMessage = itemView.findViewById(R.id.oppoMessage);
            myMessage = itemView.findViewById(R.id.myMessage);
            oppoTime = itemView.findViewById(R.id.oppoMsgTime);
            myTime = itemView.findViewById(R.id.myMsgTime);

        }
    }
}
