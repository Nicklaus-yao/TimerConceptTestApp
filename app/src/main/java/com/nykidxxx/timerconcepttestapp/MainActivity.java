package com.nykidxxx.timerconcepttestapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.timerValue) TextView timerValue;
    @BindView(R.id.populationValue) TextView populationValue;
    @BindView(R.id.foodLabel) TextView foodLabel;
    @BindView(R.id.foodValue) TextView foodValue;
    @BindView(R.id.waterLabel) TextView waterLabel;
    @BindView(R.id.waterValue) TextView waterValue;
    @BindView(R.id.woodLabel) TextView woodLabel;
    @BindView(R.id.woodValue) TextView woodValue;
    @BindView(R.id.stoneLabel) TextView stoneLabel;
    @BindView(R.id.stoneValue) TextView stoneValue;

    @BindView(R.id.unassignedValue) TextView unassignedValue;
    @BindView(R.id.foodLaborLabel) TextView foodLaborLabel;
    @BindView(R.id.foodLaborValue) TextView foodLaborValue;
    @BindView(R.id.waterLaborLabel) TextView waterLaborLabel;
    @BindView(R.id.waterLaborValue) TextView waterLaborValue;
    @BindView(R.id.woodLaborLabel) TextView woodLaborLabel;
    @BindView(R.id.woodLaborValue) TextView woodLaborValue;
    @BindView(R.id.stoneLaborLabel) TextView stoneLaborLabel;
    @BindView(R.id.stoneLaborValue) TextView stoneLaborValue;

    long startTime = 0;
    long populationCount;
    long unassignedCount;
    long foodCount;
    long waterCount;
    long foodLaborCount;
    long waterLaborCount;
    long woodCount;
    long stoneCount;
    long woodLaborCount;
    long stoneLaborCount;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            //Calculate values
            timerValue.setText(String.format("%d:%02d", minutes, seconds));
            if(seconds == 0) {
                populationCount += 1;
                unassignedCount += 1;
            } else if(seconds%10 == 0) {
                foodCount = foodCount - populationCount * 3 + foodLaborCount * 4;
                waterCount = waterCount - populationCount + waterLaborCount * 10;
            }
            woodCount = woodCount + woodLaborCount;
            stoneCount = stoneCount + stoneLaborCount;

            //Display Values
            populationValue.setText(populationCount+"");
            unassignedValue.setText(unassignedCount+"");
            foodValue.setText(foodCount+"");
            waterValue.setText(waterCount+"");
            woodValue.setText(woodCount+"");
            stoneValue.setText(stoneCount+"");

            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        timerValue = (TextView) findViewById(R.id.timerValue);
        populationValue = (TextView) findViewById(R.id.populationValue);
        populationCount = 3;
        unassignedCount = 3;
        foodCount = 1000;
        waterCount = 1000;
        foodLaborCount = 0;
        waterLaborCount = 0;
        woodCount = 0;
        stoneCount = 0;
        woodLaborCount = 0;
        stoneLaborCount = 0;

        startTime = System.currentTimeMillis();
        timerRunnable.run();
    }

    @OnClick (R.id.incFoodButton)
    public void getFoodButtonClicked(View view){
        foodCount += 1;
        foodValue.setText(foodCount+"");
    }

    @OnClick (R.id.incWaterButton)
    public void getWaterButtonClicked(View view){
        waterCount += 1;
        waterValue.setText(waterCount+"");
    }

    @OnClick (R.id.incWoodButton)
    public void getWoodButtonClicked(View view){
        woodCount += 1;
        woodValue.setText(woodCount+"");
    }

    @OnClick (R.id.incStoneButton)
    public void getStoneButtonClicked(View view){
        stoneCount += 1;
        stoneValue.setText(stoneCount+"");
    }

    @OnClick (R.id.incFoodLaborButton)
    public void incFoodLaborButtonClicked(View view){
        if(unassignedCount > 0) {
            unassignedCount -= 1;
            foodLaborCount += 1;
            foodLaborValue.setText(foodLaborCount + "");
            foodLabel.setText("Food(" + foodLaborCount + "): ");
        }
    }

    @OnClick (R.id.incWaterLaborButton)
    public void incWaterLaborButtonClicked(View view) {
        if (unassignedCount > 0) {
            unassignedCount -= 1;
            waterLaborCount += 1;
            waterLaborValue.setText(waterLaborCount + "");
            waterLabel.setText("Water(" + waterLaborCount + "): ");
        }
    }

    @OnClick (R.id.incWoodLaborButton)
    public void incWoodLaborButtonClicked(View view){
        if(unassignedCount > 0) {
            unassignedCount -= 1;
            woodLaborCount += 1;
            woodLaborValue.setText(woodLaborCount + "");
            woodLabel.setText("Wood(" + woodLaborCount + "): ");
        }
    }

    @OnClick (R.id.incStoneLaborButton)
    public void incStoneLaborButtonClicked(View view) {
        if (unassignedCount > 0) {
            unassignedCount -= 1;
            stoneLaborCount += 1;
            stoneLaborValue.setText(stoneLaborCount + "");
            stoneLabel.setText("Stone(" + stoneLaborCount + "): ");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
