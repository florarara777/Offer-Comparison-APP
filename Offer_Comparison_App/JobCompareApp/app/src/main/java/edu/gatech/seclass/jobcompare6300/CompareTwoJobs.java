package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompareTwoJobs extends AppCompatActivity {


    Button jc2j2mm_btn, back2list_btn;

    TextView jc2_id_1_view, jc2_id_2_view;
    TextView jc2_title_1_view, jc2_title_2_view;
    TextView jc2_company_1_view, jc2_company_2_view;
    TextView jc2_city_1_view, jc2_city_2_view;
    TextView jc2_state_1_view, jc2_state_2_view;
    TextView jc2_commute_time_1_view, jc2_commute_time_2_view;
    TextView jc2_yearly_salary_1_view, jc2_yearly_salary_2_view;
    TextView jc2_yearly_bonus_1_view, jc2_yearly_bonus_2_view;
    TextView jc2_retirement_benefits_1_view, jc2_retirement_benefits_2_view;
    TextView jc2_leave_time_1_view, jc2_leave_time_2_view;

    String jc2_id_1, jc2_id_2;
    String jc2_title_1, jc2_title_2;
    String jc2_company_1, jc2_company_2;
    String jc2_city_1, jc2_city_2;
    String jc2_state_1, jc2_state_2;
    String jc2_commute_time_1, jc2_commute_time_2;
    String jc2_yearly_salary_1, jc2_yearly_salary_2;
    String jc2_yearly_bonus_1, jc2_yearly_bonus_2;
    String jc2_retirement_benefits_1, jc2_retirement_benefits_2;
    String jc2_leave_time_1, jc2_leave_time_2;


    JobDBHandler jobDB;
    int job1_id, job2_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_two_jobs);

        jobDB = new JobDBHandler(CompareTwoJobs.this);

        jc2_id_1_view = findViewById(R.id.jc2_id_1_view);
        jc2_title_1_view = findViewById(R.id.jc2_title_1_view);
        jc2_company_1_view = findViewById(R.id.jc2_company_1_view);
        jc2_city_1_view = findViewById(R.id.jc2_city_1_view);
        jc2_state_1_view = findViewById(R.id.jc2_state_1_view);
        jc2_commute_time_1_view = findViewById(R.id.jc2_commute_time_1_view);
        jc2_yearly_salary_1_view = findViewById(R.id.jc2_yearly_salary_1_view);
        jc2_yearly_bonus_1_view = findViewById(R.id.jc2_yearly_bonus_1_view);
        jc2_retirement_benefits_1_view = findViewById(R.id.jc2_retirement_benefits_1_view);
        jc2_leave_time_1_view = findViewById(R.id.jc2_leave_time_1_view);


        jc2_id_2_view = findViewById(R.id.jc2_id_2_view);
        jc2_title_2_view = findViewById(R.id.jc2_title_2_view);
        jc2_company_2_view = findViewById(R.id.jc2_company_2_view);
        jc2_city_2_view = findViewById(R.id.jc2_city_2_view);
        jc2_state_2_view = findViewById(R.id.jc2_state_2_view);
        jc2_commute_time_2_view = findViewById(R.id.jc2_commute_time_2_view);
        jc2_yearly_salary_2_view = findViewById(R.id.jc2_yearly_salary_2_view);
        jc2_yearly_bonus_2_view = findViewById(R.id.jc2_yearly_bonus_2_view);
        jc2_retirement_benefits_2_view = findViewById(R.id.jc2_retirement_benefits_2_view);
        jc2_leave_time_2_view = findViewById(R.id.jc2_leave_time_2_view);

        job1_id = Integer.parseInt(getIntent().getStringExtra("id_1"));
        job2_id = Integer.parseInt(getIntent().getStringExtra("id_2"));


        getTwoJobData();

        jc2_id_1_view.setText(Integer.toString(job1_id));
        jc2_title_1_view.setText(jc2_title_1);
        jc2_company_1_view.setText(jc2_company_1);
        jc2_city_1_view.setText(jc2_city_1);
        jc2_state_1_view.setText(jc2_state_1);
        jc2_commute_time_1_view.setText(jc2_commute_time_1);
        jc2_yearly_salary_1_view.setText(jc2_yearly_salary_1);
        jc2_yearly_bonus_1_view.setText(jc2_yearly_bonus_1);
        jc2_retirement_benefits_1_view.setText(jc2_retirement_benefits_1);
        jc2_leave_time_1_view.setText(jc2_leave_time_1);

        jc2_id_2_view.setText(Integer.toString(job2_id));
        jc2_title_2_view.setText(jc2_title_2);
        jc2_company_2_view.setText(jc2_company_2);
        jc2_city_2_view.setText(jc2_city_2);
        jc2_state_2_view.setText(jc2_state_2);
        jc2_commute_time_2_view.setText(jc2_commute_time_2);
        jc2_yearly_salary_2_view.setText(jc2_yearly_salary_2);
        jc2_yearly_bonus_2_view.setText(jc2_yearly_bonus_2);
        jc2_retirement_benefits_2_view.setText(jc2_retirement_benefits_2);
        jc2_leave_time_2_view.setText(jc2_leave_time_2);


        // go to main menu
        Button jc2j2mm_btn = findViewById(R.id.jc2j2mm_btn);
        jc2j2mm_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(CompareTwoJobs.this, MainActivity.class);

                startActivity(nextScreen);
            }
        });

        // go to job list
        Button back2list_btn = findViewById(R.id.back2list_btn);
        back2list_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(CompareTwoJobs.this, JobComparison.class);

                startActivity(nextScreen);

            }
        });

    }



    void getTwoJobData(){
        Cursor cursor = jobDB.readJob(job1_id);
        int lci = jobDB.getCurrentJobCostIndex();
        double adjusted_lci_1, adjusted_lci_2;

        if(cursor.getCount() == 0){
            Toast.makeText(this, "selected job does not exist", Toast.LENGTH_LONG).show();
        } else {
            cursor.moveToNext();
            jc2_title_1 = cursor.getString(2);
            jc2_company_1 = cursor.getString(3);
            jc2_city_1 = cursor.getString(4);
            jc2_state_1 = cursor.getString(5);

            adjusted_lci_1 = Double.parseDouble(cursor.getString(6)) / (double)lci;

            jc2_commute_time_1 = cursor.getString(7);

            jc2_yearly_salary_1 = String.format("%.2f", Double.parseDouble(cursor.getString(8))/adjusted_lci_1);
            jc2_yearly_bonus_1 = String.format("%.2f", Double.parseDouble(cursor.getString(9))/adjusted_lci_1);
            jc2_retirement_benefits_1 = cursor.getString(10);
            jc2_leave_time_1 = cursor.getString(11);
        }

        Cursor cursor2 = jobDB.readJob(job2_id);

        if(cursor2.getCount() == 0){
            Toast.makeText(this, "selected job does not exist", Toast.LENGTH_LONG).show();
        } else {
            cursor2.moveToNext();

            jc2_title_2 = cursor2.getString(2);
            jc2_company_2 = cursor2.getString(3);
            jc2_city_2 = cursor2.getString(4);
            jc2_state_2 = cursor2.getString(5);

            adjusted_lci_2 = Double.parseDouble(cursor2.getString(6)) / (double)lci;

            jc2_commute_time_2 = cursor2.getString(7);

            jc2_yearly_salary_2 = String.format("%.2f", Double.parseDouble(cursor2.getString(8))* adjusted_lci_2);
            jc2_yearly_bonus_2 = String.format("%.2f", Double.parseDouble(cursor2.getString(9))*adjusted_lci_2);



            jc2_retirement_benefits_2 = cursor2.getString(10);
            jc2_leave_time_2 = cursor2.getString(11);
        }
    }


}