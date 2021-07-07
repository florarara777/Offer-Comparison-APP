package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class WeightAdjust extends AppCompatActivity {

    TextView tvCommuteTimeWeight;
    TextView tvYearlySalaryWeight;
    TextView tvYearlyBonusWeight;
    TextView tvRetirementBenefitsWeight;
    TextView tvLeaveTimeWeight;

    SeekBar sbCommuteTimeWeight;
    SeekBar sbYearlySalaryWeight;
    SeekBar sbYearlyBonusWeight;
    SeekBar sbRetirementBenefitsWeight;
    SeekBar sbLeaveTimeWeight;

//    Button btnWeightCancel, btnWeightSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_adjust);

        Weights weights = Weights.getInstance();

        // set initialized weights
        tvCommuteTimeWeight = findViewById(R.id.commuteTimeWeightTextView);
        tvYearlySalaryWeight= findViewById(R.id.yearlySalaryWeightTextView);
        tvYearlyBonusWeight= findViewById(R.id.yearlyBonusWeightTextView);
        tvRetirementBenefitsWeight= findViewById(R.id.retirementBenefitsWeightTextView);
        tvLeaveTimeWeight= findViewById(R.id.leaveTimeWeightTextView);

//        btnWeightSave = findViewById(R.id.weightSaveButton);

        // commute time
        sbCommuteTimeWeight = findViewById(R.id.commuteTimeWeightSB);

        sbCommuteTimeWeight.setProgress(weights.getCommuteTimeWeight());
        String commuteTime = Integer.toString(weights.getCommuteTimeWeight());
        String CommuteTime_String = "Commute Time Weight:   " + commuteTime;
        tvCommuteTimeWeight.setText(CommuteTime_String);

        sbCommuteTimeWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Weights weights = Weights.getInstance();
                weights.setCommuteTimeWeight(progress);
                String commuteTime = Integer.toString(weights.getCommuteTimeWeight());
                String CommuteTime_String = "Commute Time Weight:   " + commuteTime;
                tvCommuteTimeWeight.setText(CommuteTime_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // yearly salary weight
        sbYearlySalaryWeight = findViewById(R.id.yearlySalaryWeightSB);
        sbYearlySalaryWeight.setProgress(weights.getYearlySalaryWeight());
        String yearlySalary = Integer.toString(weights.getYearlySalaryWeight());
        String yearlySalary_String = "Yearly Salary Weight:   " + yearlySalary;
        tvYearlySalaryWeight.setText(yearlySalary_String);

        sbYearlySalaryWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Weights weights = Weights.getInstance();
                weights.setYearlySalaryWeight(progress);
                String yearlySalary = Integer.toString(weights.getYearlySalaryWeight());
                String yearlySalary_String = "Yearly Salary Weight:   " + yearlySalary;
                tvYearlySalaryWeight.setText(yearlySalary_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // yearly bonus weight
        sbYearlyBonusWeight = findViewById(R.id.yearlyBonusWeightSB);
        sbYearlyBonusWeight.setProgress(weights.getYearlyBonusWeight());
        String yearlyBonus = Integer.toString(weights.getYearlyBonusWeight());
        String yearlyBonus_String = "Yearly Bonus Weight:   " + yearlyBonus;
        tvYearlyBonusWeight.setText(yearlyBonus_String);

        sbYearlyBonusWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Weights weights = Weights.getInstance();
                weights.setYearlyBonusWeight(progress);
                String yearlyBonus = Integer.toString(weights.getYearlyBonusWeight());
                String yearlyBonus_String = "Yearly Bonus Weight:   " + yearlyBonus;
                tvYearlyBonusWeight.setText(yearlyBonus_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // retirement benefits
        sbRetirementBenefitsWeight = findViewById(R.id.retirementBenefitsWeightSB);
        sbRetirementBenefitsWeight.setProgress(weights.getRetirementBenefitsWeight());
        String retirementBenefits = Integer.toString(weights.getRetirementBenefitsWeight());
        String retirementBenefits_String = "Retirement Benefits Weight:   " + retirementBenefits;
        tvRetirementBenefitsWeight.setText(retirementBenefits_String);

        sbRetirementBenefitsWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Weights weights = Weights.getInstance();
                weights.setRetirementBenefitsWeight(progress);
                String retirementBenefits = Integer.toString(weights.getRetirementBenefitsWeight());
                String retirementBenefits_String = "Retirement Benefits Weight:   " + retirementBenefits;
                tvRetirementBenefitsWeight.setText(retirementBenefits_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // leave time
        sbLeaveTimeWeight = findViewById(R.id.leaveTimeWeightSB);
        sbLeaveTimeWeight.setProgress(weights.getLeaveTimeWeight());
        String leaveTime= Integer.toString(weights.getLeaveTimeWeight());
        String leaveTime_String = "Retirement Benefits Weight:   " + leaveTime;
        tvLeaveTimeWeight.setText(leaveTime_String);

        sbLeaveTimeWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Weights weights = Weights.getInstance();
                weights.setLeaveTimeWeight(progress);
                String leaveTime = Integer.toString(weights.getRetirementBenefitsWeight());
                String leaveTime_String = "Retirement Benefits Weight:   " + leaveTime;
                tvRetirementBenefitsWeight.setText(leaveTime_String);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // go to main menu
        Button btnWA2MM = findViewById(R.id.wa2mmbutton);
        btnWA2MM.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(WeightAdjust.this, MainActivity.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

    }


}