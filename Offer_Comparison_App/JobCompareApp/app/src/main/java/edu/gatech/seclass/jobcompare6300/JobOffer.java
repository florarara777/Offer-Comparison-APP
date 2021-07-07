package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class JobOffer extends AppCompatActivity {

    Utils utils = new Utils();

    EditText jo_title_text;
    EditText jo_company_text;
    EditText jo_city_text;
    EditText jo_state_text;
    EditText jo_living_cost_index_text;
    EditText jo_commute_time_text;
    EditText jo_yearly_salary_text;
    EditText jo_yearly_bonus_text;
    EditText jo_retirement_benefits_text;
    EditText jo_leave_time_text;

    Button jo_save_btn, jo_cancel_btn, jo_add_another_btn;
    Button jo_compare_cj_btn;

    JobDBHandler jobDB;

    private boolean currentJobExist = false, latestJobOfferExist = false;
    private boolean latestJobOfferSaved = false;

    int current_job_id;
    int latest_job_offer_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);

        jo_title_text = findViewById(R.id.jo_title_text);
        jo_company_text = findViewById(R.id.jo_company_text);
        jo_city_text = findViewById(R.id.jo_city_text);
        jo_state_text = findViewById(R.id.jo_state_text);
        jo_living_cost_index_text = findViewById(R.id.jo_living_cost_index_text);
        jo_commute_time_text = findViewById(R.id.jo_commute_time_text);
        jo_yearly_salary_text = findViewById(R.id.jo_yearly_salary_text);
        jo_yearly_bonus_text = findViewById(R.id.jo_yearly_bonus_text);
        jo_retirement_benefits_text = findViewById(R.id.jo_retirement_benefits_text);
        jo_leave_time_text = findViewById(R.id.jo_leave_time_text);


        // save a job offer
        jo_save_btn = findViewById(R.id.jo_save_btn);
        jo_save_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                JobDBHandler jobDB = new JobDBHandler(JobOffer.this);

                // input check
                if(!checkInputValid()) {
                    jobDB.addJobOffer(
                            jo_title_text.getText().toString(),
                            jo_company_text.getText().toString(),

                            jo_city_text.getText().toString(),
                            jo_state_text.getText().toString(),
                            Integer.valueOf(jo_living_cost_index_text.getText().toString()),
                            Double.valueOf(jo_commute_time_text.getText().toString()),
                            Double.valueOf(jo_yearly_salary_text.getText().toString()),
                            Double.valueOf(jo_yearly_bonus_text.getText().toString()),
                            Double.valueOf(jo_retirement_benefits_text.getText().toString()),
                            Integer.valueOf(jo_leave_time_text.getText().toString()));

                    latestJobOfferSaved = true;
                }
            }

        });



        jo_compare_cj_btn = findViewById(R.id.jo_compare_cj_btn);
        jo_compare_cj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobDB = new JobDBHandler(JobOffer.this);
                // check if the current job exists
                checkCurrentJob();
                // get current job just saved
                checkLatestJobOffer();
                // check the latest input job saved

                if(currentJobExist && latestJobOfferExist && latestJobOfferSaved){
                    String id1 = Integer.toString(current_job_id);
                    String id2 = Integer.toString(latest_job_offer_id);

                    Intent intent = new Intent(JobOffer.this, OfferCurrentComparison.class);
                    intent.putExtra("id_1_jo", id1);
                    intent.putExtra("id_2_jo", id2);
                    startActivity(intent);
                }
            }
        });


        // cancel the job offer edit
        jo_cancel_btn = findViewById(R.id.jo_cancel_btn);
        jo_cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                jo_title_text.setText("");
                jo_company_text.setText("");
                jo_city_text.setText("");
                jo_state_text.setText("");
                jo_living_cost_index_text.setText("");
                jo_commute_time_text.setText("");
                jo_yearly_salary_text.setText("");
                jo_yearly_bonus_text.setText("");
                jo_retirement_benefits_text.setText("");
                jo_leave_time_text.setText("");

                jo_title_text.setHint("input title");
                jo_company_text.setHint("input company");
                jo_city_text.setHint("input city");
                jo_state_text.setHint("input state");
                jo_living_cost_index_text.setHint("input living cost index");
                jo_commute_time_text.setHint("input commute time");
                jo_yearly_salary_text.setHint("input yearly salary");
                jo_yearly_bonus_text.setHint("input yearly bonus");
                jo_retirement_benefits_text.setHint("input retirement benefits");
                jo_leave_time_text.setHint("input leaving time");
            }
        });


        // add another job Offer
        jo_add_another_btn = findViewById(R.id.jo_add_another_btn);
        jo_add_another_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                jo_title_text.setText("");
                jo_company_text.setText("");
                jo_city_text.setText("");
                jo_state_text.setText("");
                jo_living_cost_index_text.setText("");
                jo_commute_time_text.setText("");
                jo_yearly_salary_text.setText("");
                jo_yearly_bonus_text.setText("");
                jo_retirement_benefits_text.setText("");
                jo_leave_time_text.setText("");

                jo_title_text.setHint("input title");
                jo_company_text.setHint("input company");
                jo_city_text.setHint("input city");
                jo_state_text.setHint("input state");
                jo_living_cost_index_text.setHint("input living cost index");
                jo_commute_time_text.setHint("input commute time");
                jo_yearly_salary_text.setHint("input yearly salary");
                jo_yearly_bonus_text.setHint("input yearly bonus");
                jo_retirement_benefits_text.setHint("input retirement benefits");
                jo_leave_time_text.setHint("input leaving time");

                latestJobOfferSaved = false;
            }

        });


        // go to main menu
        Button btnJO2mm = findViewById(R.id.jo2mmbutton);
        btnJO2mm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(JobOffer.this, MainActivity.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

    }

    //
    void checkCurrentJob(){
        Cursor cursor = jobDB.readCurrentJob();
        if(cursor.getCount() == 0){
            makeText(this, "No current job, please input a current job", Toast.LENGTH_LONG).show();
            currentJobExist = false;
        } else {
            cursor.moveToNext();
            current_job_id = Integer.parseInt(cursor.getString(0)); // to get the current job id
            currentJobExist = true;
        }
    }

    void checkLatestJobOffer(){
        Cursor cursor = jobDB.readLatestJobOffer();
        if(cursor.getCount() == 0){
            makeText(this, "No job offers, please input a job", Toast.LENGTH_LONG).show();
            latestJobOfferExist = false;
        } else {
            cursor.moveToNext();
            latest_job_offer_id = Integer.parseInt(cursor.getString(0)); // to get the current job id
            latestJobOfferExist = true;
        }
    }

    boolean checkInputValid(){
        boolean isError = false;

        String jo_title = jo_title_text.getText().toString();
        String jo_company = jo_company_text.getText().toString();
        String jo_city = jo_city_text.getText().toString();
        String jo_state = jo_state_text.getText().toString();
        String jo_living_cost_index = jo_living_cost_index_text.getText().toString();
        String jo_commute_time = jo_commute_time_text.getText().toString();
        String jo_yearly_salary = jo_yearly_salary_text.getText().toString();
        String jo_yearly_bonus = jo_yearly_bonus_text.getText().toString();
        String jo_retirement_benefits = jo_retirement_benefits_text.getText().toString();
        String jo_leave_time = jo_leave_time_text.getText().toString();

        //
        if ((utils.isEmptyNull(jo_title)) || (!utils.containAlphabet(jo_title))){
            jo_title_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_company)) || (!utils.containAlphabet(jo_company))){
            jo_company_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_city)) || (!utils.containAlphabet(jo_city))){
            jo_city_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_state)) || (!utils.containAlphabet(jo_state))){
            jo_state_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_living_cost_index)) || (!utils.isInt(jo_living_cost_index))){
            jo_living_cost_index_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_commute_time)) || (!utils.isNumeric(jo_commute_time))){
            jo_commute_time_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_yearly_salary)) || (!utils.isNumeric(jo_yearly_salary))){
            jo_yearly_salary_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_yearly_bonus)) || (!utils.isNumeric(jo_yearly_bonus))){
            jo_yearly_bonus_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_retirement_benefits)) || (!utils.isNumeric(jo_retirement_benefits))){
            jo_retirement_benefits_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(jo_leave_time)) || (!utils.isInt(jo_leave_time))){
            jo_leave_time_text.setError("input a valid one");
            isError = true;
        }

        return isError;

    }

}