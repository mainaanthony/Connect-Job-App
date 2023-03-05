package com.connect.connecticutapp.jobPerEmployer;

import android.app.AlertDialog;
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
import com.connect.connecticutapp.employer.JobsPerEmployerAdapter;
import com.connect.connecticutapp.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class jobperEmployeradapterTesr extends FirebaseRecyclerAdapter<Data , jobperEmployeradapterTesr.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public jobperEmployeradapterTesr(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Data model) {


        holder.name.setText(model.getJobTitle());
        holder.contact.setText(model.getJobContact());
        holder.salary.setText(model.getJobSalary());
//        holder.skills.setText(model.getJobSkills());
//        holder.description.setText(model.getJobDescription());



        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
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

                title.setText(model.getJobTitle());
                companyName.setText(model.getCompanyName());
                jobType.setText(model.getJobType());
                location.setText(model.getJobLocation());
                contact.setText(model.getJobContact());
                salary.setText(model.getJobSalary());
                skillsRequired.setText(model.getJobSkills());
                companyWebsite.setText(model.getJobWebsite());
                certificatesRequired.setText(model.getJobDescription());
                description.setText(model.getJobDescription());


                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("jobTitle", title.getText().toString());
                        map.put("companyName",companyName.getText().toString());
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

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_per_employer, parent, false);
        return new myViewHolder(v);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView name, contact, salary, skills, description;
        Button btnEdit, btnDelete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.post_title_per_employer);
            contact = (TextView) itemView.findViewById(R.id.post_contact_per_employer);
            salary = (TextView) itemView.findViewById(R.id.post_salary_per_emlployer);
//            skills = (TextView) itemView.findViewById(R.id.post_skills_per_employer);
//            description = (TextView) itemView.findViewById(R.id.post_description_per_employer);


            btnEdit = (Button) itemView.findViewById(R.id.post_job_edit);
            btnDelete = (Button) itemView.findViewById(R.id.post_job_delete);
        }
    }
}
