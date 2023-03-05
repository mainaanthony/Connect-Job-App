package com.connect.connecticutapp.Articles;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.connecticutapp.R;
import com.connect.connecticutapp.Articles.articlesPerJobSeekerAdapter;
import com.connect.connecticutapp.employer.JobsPerEmployerAdapter;
import com.connect.connecticutapp.model.Data;
import com.connect.connecticutapp.model.RecyclerViewInterface;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class articlesPerJobSeekerAdapter extends FirebaseRecyclerAdapter< ArticlesData,articlesPerJobSeekerAdapter.MyViewHolder>{




    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public articlesPerJobSeekerAdapter(@NonNull FirebaseRecyclerOptions<ArticlesData> options) {


        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ArticlesData model) {


        holder.name.setText(model.getArticleTitle());
        holder.contact.setText(model.getWriterContact());
        holder.salary.setText(model.getArticleDescription());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup_articles))
                        .setExpanded(true, 1200)
                        .create();


                dialogPlus.show();


                View view = dialogPlus.getHolderView();

                EditText title = view.findViewById(R.id.textNameTitle);
                EditText companyName = view.findViewById(R.id.text_CompanyName);
                EditText jobType = view.findViewById(R.id.textJobType);
                EditText location = view.findViewById(R.id.textNameLocation);
                EditText contact = view.findViewById(R.id.textNameContact);
                EditText salary = view.findViewById(R.id.textNameSalary);
                EditText skillsRequired = view.findViewById(R.id.textNameSkillsRequired);
                EditText companyWebsite = view.findViewById(R.id.textNameCompanyWebsite);
                EditText certificatesRequired = view.findViewById(R.id.textNameCertificatesRequired);
                EditText description = view.findViewById(R.id.textNameJobDescription);


                Button btnUpdate = view.findViewById(R.id.buttonUpdateJob);

                title.setText(model.getArticleTitle());
                jobType.setText(model.getArticleDescription());
                location.setText(model.getArticleContent());
                contact.setText(model.getWriterContact());
                salary.setText(model.getWriterName());
                skillsRequired.setText(model.getWriterDesc());
                companyWebsite.setText(model.getWriterEmail());
                certificatesRequired.setText(model.getWriterWebsite());
//                description.setText(model.getJobDescription());


                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("jobTitle", title.getText().toString());
                        map.put("jobType", jobType.getText().toString());
                        map.put("jobLocation", location.getText().toString());
                        map.put("jobContact", contact.getText().toString());
                        map.put("jobSalary", salary.getText().toString());
                        map.put("jobSkills", skillsRequired.getText().toString());
                        map.put("jobWebsite", companyWebsite.getText().toString());
                        map.put("jobCertificates", certificatesRequired.getText().toString());
                        map.put("jobDescription", description.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("JobPost")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();
                                    }
                                });


                    }
                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be undone.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("JobPost")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();

            }
        });
    }


//    @Override
//    public int getItemCount() {
//        return list.size();
//    }


    @NonNull
    @Override
    public articlesPerJobSeekerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles, parent, false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,contact,salary,skills,description;
        Button btnEdit, btnDelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.post_title);
            contact = itemView.findViewById(R.id.post_contact);
            salary = itemView.findViewById(R.id.post_salary);


            btnEdit = (Button) itemView.findViewById(R.id.post_job_edit);
            btnDelete = (Button) itemView.findViewById(R.id.post_job_delete);


        }
    }

//
//
//    private  final RecyclerViewInterface recyclerViewInterface;
//
//    Context context;
//    ArrayList<ArticlesData> list;
//
//    public articlesPerJobSeekerAdapter(Context context, ArrayList<ArticlesData> list, RecyclerViewInterface recyclerViewInterface) {
//
//        this.context = context;
//        this.list = list;
//        this.recyclerViewInterface = recyclerViewInterface;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.articles, parent, false);
//        return new articlesPerJobSeekerAdapter.MyViewHolder(v, recyclerViewInterface);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        ArticlesData data = list.get(position);
//        holder.name.setText(data.getArticleTitle());
//        holder.contact.setText(data.getWriterContact());
//        holder.salary.setText(data.getArticleDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView name,contact,salary,skills,description;
//
//        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
//            super(itemView);
//
//            name = itemView.findViewById(R.id.post_title);
//            contact = itemView.findViewById(R.id.post_contact);
//            salary = itemView.findViewById(R.id.post_salary);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(articlesPerJobSeekerAdapter.this.recyclerViewInterface !=null){
//                        int pos = getBindingAdapterPosition();
//
//                        if(pos != RecyclerView.NO_POSITION){
//                            articlesPerJobSeekerAdapter.this.recyclerViewInterface.onItemClick(pos);
//                        }
//
//                    }
//                }
//            });
//
//        }
//    }
}
