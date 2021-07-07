package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class JobComparison extends AppCompatActivity {

    Utils utils = new Utils();

    RecyclerView job_list_rv;

    JobDBHandler jobDB;
    ArrayList<String> job_id_s, job_is_current_s, job_title_s, job_company_s, job_score_s;
    CustomAdapter customAdapter;

    EditText jc_id1_text, jc_id2_text;
    String id1, id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_comparison);

        job_list_rv = findViewById(R.id.jobListRecyclerView);
        jc_id1_text = findViewById(R.id.jc_id1_text);
        jc_id2_text = findViewById(R.id.jc_id2_text);


        jobDB = new JobDBHandler(JobComparison.this);
        job_id_s = new ArrayList<>();
        job_is_current_s = new ArrayList<>();
        job_title_s = new ArrayList<>();
        job_company_s = new ArrayList<>();
        job_score_s = new ArrayList<>();


        putDataToJobList();

        customAdapter = new CustomAdapter(JobComparison.this, job_id_s, job_is_current_s, job_title_s, job_company_s, job_score_s);
        job_list_rv.setAdapter(customAdapter);
        job_list_rv.setLayoutManager(new LinearLayoutManager(JobComparison.this));


        // go to main menu
        Button btnJC2mm = findViewById(R.id.jc2mmbutton);
        btnJC2mm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(JobComparison.this, MainActivity.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });



        // select two job for comparison
        Button jc_compare_btn = findViewById(R.id.jc_compare_btn);
        jc_compare_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1 = jc_id1_text.getText().toString();
                id2 = jc_id2_text.getText().toString();

                // check id1 and id2
                if(!jobIDHasError()){
                    Intent intent = new Intent(JobComparison.this, CompareTwoJobs.class);
                    intent.putExtra("id_1", id1);
                    intent.putExtra("id_2", id2);
                    startActivity(intent);
                }
            }
        });
    }

    void putDataToJobList(){

        Cursor cursor = jobDB.readRankedJobList();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                job_id_s.add(cursor.getString(0));
                job_is_current_s.add(cursor.getString(1));
                job_title_s.add(cursor.getString(2));
                job_company_s.add(cursor.getString(3));
                job_score_s.add(cursor.getString(4));
//                job_yearly_salary_s.add(cursor.getString(5));
            }
        }
    }

    boolean jobIDHasError(){
        boolean isError = false;

        if ((utils.isEmptyNull(id1)) || (!utils.isInt(id1))){
            jc_id1_text.setError("input a valid one");
            isError = true;
        }

        if ((utils.isEmptyNull(id2)) || (!utils.isInt(id2))){
            jc_id2_text.setError("input a valid one");
            isError = true;
        }

        if ((utils.isEmptyNull(id2)) || (utils.isEmptyNull(id1)) ){
            jc_id1_text.setError("input a valid one");
            jc_id2_text.setError("input a valid one");
            isError = true;
        }

        if ((!utils.isEmptyNull(id2)) || (!utils.isEmptyNull(id1))){
            Cursor cursor = jobDB.readRankedJobList();

            int id1_int = Integer.parseInt(id1);
            int id2_int = Integer.parseInt(id2);
            int count = cursor.getCount();
            if (id1_int > count) {
                jc_id1_text.setError("id is out of range");
                isError = true;
            }
            if (id2_int > count) {
                jc_id2_text.setError("id is out of range");
                isError = true;
            }
            if (id1_int == id2_int) {
                jc_id2_text.setError("choose a different id");
                isError = true;
            }
        }

        return isError;

    }

}