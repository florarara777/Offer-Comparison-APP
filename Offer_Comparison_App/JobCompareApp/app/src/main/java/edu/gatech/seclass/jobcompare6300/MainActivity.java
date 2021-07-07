package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCurrentJob = findViewById(R.id.btnCurrentJob);
        Button btnJobOffer = findViewById(R.id.btnJobOffer);
        Button btnJobComparison =  findViewById(R.id.btnJobComparison);
        Button btnAdjustWeight = findViewById(R.id.btnWeightAdjust);


        //Current Job Button
        btnCurrentJob.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(MainActivity.this, CurrentJob.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

        btnJobOffer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(MainActivity.this, JobOffer.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

        btnJobComparison.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(MainActivity.this, JobComparison.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });

        btnAdjustWeight.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(MainActivity.this, WeightAdjust.class);

                startActivity(nextScreen);
                //Log.i(TAG, "After startActivity");
            }
        });



    }
}