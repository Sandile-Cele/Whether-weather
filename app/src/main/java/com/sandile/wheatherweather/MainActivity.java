package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.AccuApiPojo.DailyForecast;
import com.AccuApiPojo.RootAccu5Day;
import com.AccuApiPojo.Temperature;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_citysearch;
    private TextView tv_cityname;
    private EditText et_cityname;
    private FloatingActionButton fab_goto_forecastList;
    // Add progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing pallets
        btn_citysearch = findViewById(R.id.main_btn_search);
        btn_citysearch.setOnClickListener(this);

        tv_cityname = findViewById(R.id.main_tv_cityname);
        et_cityname = findViewById(R.id.main_et_citysearch);

        fab_goto_forecastList = findViewById(R.id.main_fab_goto_forecastList);
        fab_goto_forecastList.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_btn_search:
                //SearchCity();
                getJsonDetails();
                break;
            case R.id.main_fab_goto_forecastList:
                finish();
                startActivity(new Intent(this, ForecastList.class));
                break;
//            case R.id.main_btn_daily_weight:
//                startActivity(new Intent(this, WeightLog.class));
//                break;
//            case R.id.main_btn_meal_log:
//                startActivity(new Intent(this, MealLog.class));
        }
    }

    private void SearchCity() {
        String CityName = et_cityname.getText().toString().trim();
        tv_cityname.setText(et_cityname.getText());
    }

    private void getJsonDetails(){
        final String BASE_URL = "https://dataservice.accuweather.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccuWeatherApi accuWeatherApi = retrofit.create(AccuWeatherApi.class);

        Call<RootAccu5Day> call = accuWeatherApi.getPosts();

        //.enqueue is a running a thread in background
        call.enqueue(new Callback<RootAccu5Day>() {
            @Override
            public void onResponse(Call<RootAccu5Day> call, Response<RootAccu5Day> response) {

                if(!response.isSuccessful()){
                    tv_cityname.setText("Code: " + response.code());
                    return;
                }

                RootAccu5Day AccuDetailsObj = response.body();

                tv_cityname.append("Description: " + AccuDetailsObj.getHeadline().getText() + "\n\n");

                for(DailyForecast oneDay: AccuDetailsObj.getDailyForecasts()){
                    int i = 1;
                    tv_cityname.append("Day " + i++ + " Max temp: " + oneDay.temperature.maximum.getValue() + "\n");
                }

            }

            @Override
            public void onFailure(Call<RootAccu5Day> call, Throwable t) {
                tv_cityname.setText(t.getMessage());
            }
        });
    }

}