package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.AccuPojo.Accu5DayPojo.DailyForecast;
import com.AccuPojo.Accu5DayPojo.RootAccu5Day;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sandile.wheatherweather.AccuApi.ApiEngine;
import com.sandile.wheatherweather.AccuApi.IAccuWeatherApi;
import com.sandile.wheatherweather.RecyclerView.RecyclerViewAdapter5Day;
import com.sandile.wheatherweather.RecyclerView.SingleForecastItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastList extends AppCompatActivity implements View.OnClickListener {//This is a view
    private FloatingActionButton fab_goto_main;
    private ProgressBar pb_ladingList;
    private TextView tv_forecastList;

    private RecyclerView rv_5DayForecast;
    private RecyclerView.Adapter rv_5DayForecast_Adapter;//This is bridge between data and recyclerView.//It also helps with performance by only providing RV with the data that is need(can be viewed)
    private RecyclerView.LayoutManager rv_5DayForecast_layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_list);
        //initializing pallets
        fab_goto_main = findViewById(R.id.forecastlist_fab_goto_main);
        fab_goto_main.setOnClickListener(this);

        pb_ladingList = findViewById(R.id.forecastList_pb_ladingList);

        tv_forecastList = findViewById(R.id.forecastList_tv_forecastList);

        rv_5DayForecast = findViewById(R.id.forecastList_rv_5DayForecast);
        //DONE initializing pallets

//CODE TO SHOW FORECAST ON LIST. int tempCityKey = ApiEngine.tempCityKey;// For now I have manually set this to "2884280"(Dubai)
//                               ForecastApi(tempCityKey, new ApiEngine().RetrofitBuildBase());



                //Rv logic
        ArrayList<SingleForecastItem> singleForecastListObj = new ArrayList<>();
        singleForecastListObj.add(new SingleForecastItem(R.drawable.ic_sun, "Max temp: 60, Min: 30", "Date: 15/03/2021"));
        singleForecastListObj.add(new SingleForecastItem(R.drawable.ic_umbrella, "Max temp: 61, Min: 31", "Date: 16/03/2021"));
        singleForecastListObj.add(new SingleForecastItem(R.drawable.ic_cloud, "Max temp: 62, Min: 32", "Date: 17/03/2021"));

        rv_5DayForecast_layoutManager = new LinearLayoutManager(this);//initializing manager
        rv_5DayForecast.setHasFixedSize(true);//Set to true in increase performance. !!If set to false(or not set) will load all the data into RV, but now because "true" will only load what can be viewed on screen.

        rv_5DayForecast.setLayoutManager(rv_5DayForecast_layoutManager);

        rv_5DayForecast_Adapter = new RecyclerViewAdapter5Day(singleForecastListObj);//initializing adapter
        rv_5DayForecast.setAdapter(rv_5DayForecast_Adapter);

        Log.d("Editable", "charAt");

                //Rv logic done

        //Proof API is working
//        Accu5Day(ApiEngine.tempCityKey, new ApiEngine().RetrofitBuildBase());
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forecastlist_fab_goto_main:
//                finish(); //This is to close activity
                startActivity(new Intent(this, MainActivity.class));
        }
    }

    //This must be API engine, but cant move it there because It's impossible to return values from
    private void Accu5Day(int cityKey, IAccuWeatherApi oneIAccuObj) {
        pb_ladingList.setVisibility(View.VISIBLE);

        Call<RootAccu5Day> forecastObj = oneIAccuObj.get5DayForecast(cityKey);

        forecastObj.enqueue(new Callback<RootAccu5Day>() {
            @Override
            public void onResponse(Call<RootAccu5Day> call, Response<RootAccu5Day> response) {
                pb_ladingList.setVisibility(View.GONE);

                if(!response.isSuccessful()){//If error occurs return
                    tv_forecastList.setText("Code: " + response.code());
                    return;
                }

                RootAccu5Day AccuDetailsObj = response.body();

                tv_forecastList.append(AccuDetailsObj.getHeadline().getText() + "\n");

                for(DailyForecast oneDay: AccuDetailsObj.getDailyForecasts()){
                    tv_forecastList.append(oneDay.date.toString() + " Max temp: " + oneDay.temperature.maximum.getValue() + "\n");
                }
            }

            @Override
            public void onFailure(Call<RootAccu5Day> call, Throwable t) {
                pb_ladingList.setVisibility(View.GONE);
                tv_forecastList.setText(t.getMessage());
            }
        });

    }

    private void LoadRecyclerView(){

    }

}