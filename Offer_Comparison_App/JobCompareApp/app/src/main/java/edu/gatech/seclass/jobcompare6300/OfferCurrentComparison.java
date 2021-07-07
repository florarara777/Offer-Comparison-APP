package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class OfferCurrentComparison extends AppCompatActivity {

    Button occ2mm_btn, back2jo_btn;

    TextView occ_id_1_view, occ_id_2_view;
    TextView occ_title_1_view, occ_title_2_view;
    TextView occ_company_1_view, occ_company_2_view;
    TextView occ_city_1_view, occ_city_2_view;
    TextView occ_state_1_view, occ_state_2_view;
    TextView occ_commute_time_1_view, occ_commute_time_2_view;
    TextView occ_yearly_salary_1_view, occ_yearly_salary_2_view;
    TextView occ_yearly_bonus_1_view, occ_yearly_bonus_2_view;
    TextView occ_retirement_benefits_1_view, occ_retirement_benefits_2_view;
    TextView occ_leave_time_1_view, occ_leave_time_2_view;

    String occ_title_1, occ_title_2;
    String occ_company_1, occ_company_2;
    String occ_city_1, occ_city_2;
    String occ_state_1, occ_state_2;
    String occ_commute_time_1, occ_commute_time_2;
    String occ_yearly_salary_1, occ_yearly_salary_2;
    String occ_yearly_bonus_1, occ_yearly_bonus_2;
    String occ_retirement_benefits_1, occ_retirement_benefits_2;
    String occ_leave_time_1, occ_leave_time_2;


    JobDBHandler jobDB;
    int job1_id, job2_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_current_comparison);

        jobDB = new JobDBHandler(OfferCurrentComparison.this);

        occ_id_1_view = findViewById(R.id.occ_id_1_view);
        occ_title_1_view = findViewById(R.id.occ_title_1_view);
        occ_company_1_view = findViewById(R.id.occ_company_1_view);
        occ_city_1_view = findViewById(R.id.occ_city_1_view);
        occ_state_1_view = findViewById(R.id.occ_state_1_view);
        occ_commute_time_1_view = findViewById(R.id.occ_commute_time_1_view);
        occ_yearly_salary_1_view = findViewById(R.id.occ_yearly_salary_1_view);
        occ_yearly_bonus_1_view = findViewById(R.id.occ_yearly_bonus_1_view);
        occ_retirement_benefits_1_view = findViewById(R.id.occ_retirement_benefits_1_view);
        occ_leave_time_1_view = findViewById(R.id.occ_leave_time_1_view);


        occ_id_2_view = findViewById(R.id.occ_id_2_view);
        occ_title_2_view = findViewById(R.id.occ_title_2_view);
        occ_company_2_view = findViewById(R.id.occ_company_2_view);
        occ_city_2_view = findViewById(R.id.occ_city_2_view);
        occ_state_2_view = findViewById(R.id.occ_state_2_view);
        occ_commute_time_2_view = findViewById(R.id.occ_commute_time_2_view);
        occ_yearly_salary_2_view = findViewById(R.id.occ_yearly_salary_2_view);
        occ_yearly_bonus_2_view = findViewById(R.id.occ_yearly_bonus_2_view);
        occ_retirement_benefits_2_view = findViewById(R.id.occ_retirement_benefits_2_view);
        occ_leave_time_2_view = findViewById(R.id.occ_leave_time_2_view);

        job1_id = Integer.parseInt(getIntent().getStringExtra("id_1_jo"));
        job2_id = Integer.parseInt(getIntent().getStringExtra("id_2_jo"));

        getTwoJobData();

        occ_id_1_view.setText(Integer.toString(job1_id));
        occ_title_1_view.setText(occ_title_1);
        occ_company_1_view.setText(occ_company_1);
        occ_city_1_view.setText(occ_city_1);
        occ_state_1_view.setText(occ_state_1);
        occ_commute_time_1_view.setText(occ_commute_time_1);
        occ_yearly_salary_1_view.setText(occ_yearly_salary_1);
        occ_yearly_bonus_1_view.setText(occ_yearly_bonus_1);
        occ_retirement_benefits_1_view.setText(occ_retirement_benefits_1);
        occ_leave_time_1_view.setText(occ_leave_time_1);

        occ_id_2_view.setText(Integer.toString(job2_id));
        occ_title_2_view.setText(occ_title_2);
        occ_company_2_view.setText(occ_company_2);
        occ_city_2_view.setText(occ_city_2);
        occ_state_2_view.setText(occ_state_2);
        occ_commute_time_2_view.setText(occ_commute_time_2);
        occ_yearly_salary_2_view.setText(occ_yearly_salary_2);
        occ_yearly_bonus_2_view.setText(occ_yearly_bonus_2);
        occ_retirement_benefits_2_view.setText(occ_retirement_benefits_2);
        occ_leave_time_2_view.setText(occ_leave_time_2);


        // go to main menu
        Button occ2mm_btn = findViewById(R.id.occ2mm_btn);
        occ2mm_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(OfferCurrentComparison.this, MainActivity.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

        // go to job list
        Button back2jo_btn = findViewById(R.id.back2jo_btn);
        back2jo_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(OfferCurrentComparison.this, JobOffer.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
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
            occ_title_1 = cursor.getString(2);
            occ_company_1 = cursor.getString(3);
            occ_city_1 = cursor.getString(4);
            occ_state_1 = cursor.getString(5);

            adjusted_lci_1 = Double.parseDouble(cursor.getString(6)) / (double)lci;

            occ_commute_time_1 = cursor.getString(7);

            occ_yearly_salary_1 = String.format("%.2f", Double.parseDouble(cursor.getString(8))/adjusted_lci_1);
            occ_yearly_bonus_1 = String.format("%.2f", Double.parseDouble(cursor.getString(9))/adjusted_lci_1);
            occ_retirement_benefits_1 = cursor.getString(10);
            occ_leave_time_1 = cursor.getString(11);
        }

        Cursor cursor2 = jobDB.readJob(job2_id);

        if(cursor2.getCount() == 0){
            Toast.makeText(this, "selected job does not exist", Toast.LENGTH_LONG).show();
        } else {
            cursor2.moveToNext();

            occ_title_2 = cursor2.getString(2);
            occ_company_2 = cursor2.getString(3);
            occ_city_2 = cursor2.getString(4);
            occ_state_2 = cursor2.getString(5);

            adjusted_lci_2 = Double.parseDouble(cursor2.getString(6)) / (double)lci;

            occ_commute_time_2 = cursor2.getString(7);

            occ_yearly_salary_2 = String.format("%.2f", Double.parseDouble(cursor2.getString(8))/adjusted_lci_2);
            occ_yearly_bonus_2 = String.format("%.2f", Double.parseDouble(cursor2.getString(9))/adjusted_lci_2);

            occ_retirement_benefits_2 = cursor2.getString(10);
            occ_leave_time_2 = cursor2.getString(11);
        }

    }
}

