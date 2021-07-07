package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentJob extends AppCompatActivity {

    Utils utils = new Utils();

    EditText cj_title_text;
    EditText cj_company_text;
    EditText cj_city_text;
    EditText cj_state_text;
    EditText cj_living_cost_index_text;
    EditText cj_commute_time_text;
    EditText cj_yearly_salary_text;
    EditText cj_yearly_bonus_text;
    EditText cj_retirement_benefits_text;
    EditText cj_leave_time_text;


    Button cj_save_btn, cj_cancel_btn;
    JobDBHandler jobDB;

    String cj_title;
    String cj_company;
    String cj_city;
    String cj_state;
    String cj_living_cost;
    String cj_commute_time;
    String cj_yearly_salary;
    String cj_yearly_bonus;
    String cj_retirement_benefits;
    String cj_leave_time;

    private boolean currentJobExist = false;
    int current_job_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);

        jobDB = new JobDBHandler(CurrentJob.this);

        cj_title_text = findViewById(R.id.cj_title_text);
        cj_company_text = findViewById(R.id.cj_company_text);
        cj_city_text = findViewById(R.id.cj_city_text);
        cj_state_text = findViewById(R.id.cj_state_text);
        cj_living_cost_index_text = findViewById(R.id.cj_living_cost_index_text);

        cj_commute_time_text = findViewById(R.id.cj_commute_time_text);
        cj_yearly_salary_text = findViewById(R.id.cj_yearly_salary_text);
        cj_yearly_bonus_text = findViewById(R.id.cj_yearly_bonus_text);
        cj_retirement_benefits_text = findViewById(R.id.cj_retirement_benefits_text);
        cj_leave_time_text = findViewById(R.id.cj_leave_time_text);

        currentJobExist = getCurrentJobData();

        if (!currentJobExist) {
            cj_title_text.setHint("input title");
            cj_company_text.setHint("input company");
            cj_city_text.setHint("input city");
            cj_state_text.setHint("input state");
            cj_living_cost_index_text.setHint("input living cost index");
            cj_commute_time_text.setHint("input commute time");
            cj_yearly_salary_text.setHint("input yearly salary");
            cj_yearly_bonus_text.setHint("input yearly bonus");
            cj_retirement_benefits_text.setHint("input retirement benefits");
            cj_leave_time_text.setHint("input leaving time");
        } else {
            cj_title_text.setText(cj_title);
            cj_company_text.setText(cj_company);
            cj_city_text.setText(cj_city);
            cj_state_text.setText(cj_state);
            cj_living_cost_index_text.setText(cj_living_cost);
            cj_commute_time_text.setText(cj_commute_time);
            cj_yearly_salary_text.setText(cj_yearly_salary);
            cj_yearly_bonus_text.setText(cj_yearly_bonus);
            cj_retirement_benefits_text.setText(cj_retirement_benefits);
            cj_leave_time_text.setText(cj_leave_time);
        }

        // cj cancel btn
        cj_cancel_btn = findViewById(R.id.cj_cancel_btn);
        cj_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cj_title_text.setHint("input title");
                cj_company_text.setHint("input company");
                cj_city_text.setHint("input city");
                cj_state_text.setHint("input state");
                cj_living_cost_index_text.setHint("input living cost index");
                cj_commute_time_text.setHint("input commute time");
                cj_yearly_salary_text.setHint("input yearly salary");
                cj_yearly_bonus_text.setHint("input yearly bonus");
                cj_retirement_benefits_text.setHint("input retirement benefits");
                cj_leave_time_text.setHint("input leaving time");

                cj_title_text.setText(cj_title);
                cj_company_text.setText(cj_company);
                cj_city_text.setText(cj_city);
                cj_state_text.setText(cj_state);
                cj_living_cost_index_text.setText(cj_living_cost);
                cj_commute_time_text.setText(cj_commute_time);
                cj_yearly_salary_text.setText(cj_yearly_salary);
                cj_yearly_bonus_text.setText(cj_yearly_bonus);
                cj_retirement_benefits_text.setText(cj_retirement_benefits);
                cj_leave_time_text.setText(cj_leave_time);

            }
        });

        // cj save btn
        cj_save_btn = findViewById(R.id.cj_save_btn);
        cj_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JobDBHandler jobDB = new JobDBHandler( CurrentJob.this);

                cj_title = cj_title_text.getText().toString();
                cj_company = cj_company_text.getText().toString();
                cj_city = cj_city_text.getText().toString();
                cj_state = cj_state_text.getText().toString();
                cj_living_cost = cj_living_cost_index_text.getText().toString();
                cj_commute_time = cj_commute_time_text.getText().toString();
                cj_yearly_salary = cj_yearly_salary_text.getText().toString();
                cj_yearly_bonus = cj_yearly_bonus_text.getText().toString();
                cj_retirement_benefits = cj_retirement_benefits_text.getText().toString();
                cj_leave_time = cj_leave_time_text.getText().toString();


                if(!checkInputValid()) {
                    if (!currentJobExist){
                        jobDB.addCurrentJob(
                                cj_title,
                                cj_company,
                                cj_city,
                                cj_state,
                                Integer.valueOf(cj_living_cost),
                                Double.valueOf(cj_commute_time),
                                Double.valueOf(cj_yearly_salary),
                                Double.valueOf(cj_yearly_bonus),
                                Double.valueOf(cj_retirement_benefits),
                                Integer.valueOf(cj_leave_time));
                    } else {
                        // need to updateCurrentJob
                        current_job_id = getCurrentJobIndex(); // in order to set the current job id.
                        jobDB.updateCurrentJobData(
                                current_job_id,
                                cj_title,
                                cj_company,
                                cj_city,
                                cj_state,
                                Integer.valueOf(cj_living_cost),
                                Double.valueOf(cj_commute_time),
                                Double.valueOf(cj_yearly_salary),
                                Double.valueOf(cj_yearly_bonus),
                                Double.valueOf(cj_retirement_benefits),
                                Integer.valueOf(cj_leave_time));

                    }
                    cj_title_text.setText(cj_title);
                    cj_company_text.setText(cj_company);
                    cj_city_text.setText(cj_city);
                    cj_state_text.setText(cj_state);
                    cj_living_cost_index_text.setText(cj_living_cost);
                    cj_commute_time_text.setText(cj_commute_time);
                    cj_yearly_salary_text.setText(cj_yearly_salary);
                    cj_yearly_bonus_text.setText(cj_yearly_bonus);
                    cj_retirement_benefits_text.setText(cj_retirement_benefits);
                    cj_leave_time_text.setText(cj_leave_time);
                }

            }
        });


        // go to main menu
        Button btnCJ2mm = findViewById(R.id.cj2mmbutton);
        btnCJ2mm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(CurrentJob.this, MainActivity.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });
    }

    boolean getCurrentJobData(){
        Cursor cursor = jobDB.readCurrentJob();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No current job, please input a current job", Toast.LENGTH_LONG).show();
            currentJobExist = false;
        } else {
            cursor.moveToNext();
            current_job_id = Integer.parseInt(cursor.getString(0)); // to get the current job id

            cj_title = cursor.getString(2);
            cj_company = cursor.getString(3);
            cj_city = cursor.getString(4);
            cj_state = cursor.getString(5);
            cj_living_cost = cursor.getString(6);
            cj_commute_time = cursor.getString(7);
            cj_yearly_salary = cursor.getString(8);
            cj_yearly_bonus = cursor.getString(9);
            cj_retirement_benefits = cursor.getString(10);
            cj_leave_time = cursor.getString(11);

            currentJobExist = true;
        }

        return currentJobExist;
    }

    int getCurrentJobIndex(){
        Cursor cursor = jobDB.readCurrentJob();

        cursor.moveToNext();
        current_job_id = Integer.parseInt(cursor.getString(0)); // to get the current job id

        return current_job_id;
    }



    boolean checkInputValid(){
        boolean isError = false;
        //
        if ((utils.isEmptyNull(cj_title)) || (!utils.containAlphabet(cj_title))){
            cj_title_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_company)) || (!utils.containAlphabet(cj_company))){
            cj_company_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_city)) || (!utils.containAlphabet(cj_city))){
            cj_city_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_state)) || (!utils.containAlphabet(cj_state))){
            cj_state_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_living_cost)) || (!utils.isInt(cj_living_cost))){
            cj_living_cost_index_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_commute_time)) || (!utils.isNumeric(cj_commute_time))){
            cj_commute_time_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_yearly_salary)) || (!utils.isNumeric(cj_yearly_salary))){
            cj_yearly_salary_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_yearly_bonus)) || (!utils.isNumeric(cj_yearly_bonus))){
            cj_yearly_bonus_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_retirement_benefits)) || (!utils.isNumeric(cj_retirement_benefits))){
            cj_retirement_benefits_text.setError("input a valid one");
            isError = true;
        }
        if ((utils.isEmptyNull(cj_leave_time)) || (!utils.isInt(cj_leave_time))){
            cj_leave_time_text.setError("input a valid one");
            isError = true;
        }

        return isError;

    }

}