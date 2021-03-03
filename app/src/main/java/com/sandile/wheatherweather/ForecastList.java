package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ForecastList extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab_goto_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_list);

        fab_goto_main = findViewById(R.id.forecastlist_fab_goto_main);
        fab_goto_main.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forecastlist_fab_goto_main:
                startActivity(new Intent(this, MainActivity.class));
        }
    }


}