package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList job_id_a, job_is_current_a, job_title_a, job_company_a, job_score_a;



    CustomAdapter(Context context,
                  ArrayList job_id_a,
                  ArrayList job_title_a,
                  ArrayList job_company_a,
                  ArrayList job_is_current_a,
                  ArrayList job_score_a){
        this.context = context;
        this.job_id_a = job_id_a;
        this.job_title_a = job_title_a;
        this.job_company_a = job_company_a;
        this.job_is_current_a = job_is_current_a;
        this.job_score_a = job_score_a;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.job_id_txt.setText(String.valueOf(job_id_a.get(position)));
        holder.job_title_txt.setText(String.valueOf(job_title_a.get(position)));
        holder.job_company_txt.setText(String.valueOf(job_company_a.get(position)));
        holder.job_is_current_txt.setText(String.valueOf(job_is_current_a.get(position)));
        holder.job_score_txt.setText(String.valueOf(job_score_a.get(position)));

    }

    @Override
    public int getItemCount() {
        return job_id_a.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView job_id_txt, job_is_current_txt, job_title_txt, job_company_txt, job_score_txt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            job_id_txt = itemView.findViewById(R.id.job_id_txt);
            job_title_txt = itemView.findViewById(R.id.job_title_txt);
            job_company_txt = itemView.findViewById(R.id.job_company_txt);
            job_is_current_txt = itemView.findViewById(R.id.job_is_current_txt);
            job_score_txt = itemView.findViewById(R.id.job_score_txt);

        }
    }
}


