package com.connect.connecticutapp;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.RecyclerViewInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<Data> list;


    public MyAdapter(Context context, ArrayList<Data> list, RecyclerViewInterface recyclerViewInterface) {

        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setFilteredList(ArrayList<Data> filteredList) {
        this.list = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.jobs, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = list.get(position);
        holder.name.setText(data.getJobTitle());
        holder.contact.setText(data.getJobContact());
        holder.salary.setText(data.getJobSalary());
        holder.coName.setText(data.getCompanyName());
        holder.description.setText(data.getDate());
        String date = data.getDate();


        long timestamp = System.currentTimeMillis();
        //        convert timestamp into dd/MM/yyyy
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(String.valueOf(timestamp)));
        String dateFormat = DateFormat.format("dd/MM/yyyy", calendar).toString();

        System.out.println("ytnnnnv" + dateFormat);


        SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date1 = dates.parse(date);

            Date date2 = dates.parse(dateFormat);


            long difference = Math.abs(date1.getTime() - date2.getTime());


            long differenceDates = difference / (24 * 60 * 60 * 1000);


            String dayDifference = Long.toString(differenceDates);
            if (Integer.parseInt(dayDifference) <= 10) {
                holder.wahala.setVisibility(View.VISIBLE);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, salary, coName, description, wahala;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.post_title);
            contact = itemView.findViewById(R.id.post_contact);
            salary = itemView.findViewById(R.id.post_salary);
            coName = itemView.findViewById(R.id.post_skills);
            description = itemView.findViewById(R.id.post_description);
            wahala = itemView.findViewById(R.id.report);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getBindingAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });


        }
    }
}
