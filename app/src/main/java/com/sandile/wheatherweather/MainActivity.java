package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Accu5DayPojo.DailyForecast;
import com.Accu5DayPojo.RootAccu5Day;
import com.AccuCitySearchPojo.RootAccuCitySearch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_citysearch;
    private TextView tv_cityname;
    private EditText et_cityname;
    private FloatingActionButton fab_goto_forecastList;
    private ProgressBar pb_cityDetails;
    private IAccuWeatherApi oneIAccuWeatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing pallets
        btn_citysearch = findViewById(R.id.main_btn_search);
        btn_citysearch.setOnClickListener(this);

        tv_cityname = findViewById(R.id.main_tv_cityname);
        et_cityname = findViewById(R.id.main_et_citysearch);

        pb_cityDetails = findViewById(R.id.main_pb_cityDetails);
        pb_cityDetails = findViewById(R.id.main_pb_cityDetails);

        fab_goto_forecastList = findViewById(R.id.main_fab_goto_forecastList);
        fab_goto_forecastList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_btn_search:
                retrofitBuildBase();
                break;
            case R.id.main_fab_goto_forecastList:
//                finish();//This is to close activity
                startActivity(new Intent(this, ForecastList.class));
                break;
        }
    }

    private String getTextFromTv() {
        return et_cityname.getText().toString().trim();
    }

    private void getJsonDetails(){
        pb_cityDetails.setVisibility(View.VISIBLE);

        final String BASE_URL = "https://dataservice.accuweather.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAccuWeatherApi IAccuWeatherApi = retrofit.create(IAccuWeatherApi.class);

        Call<RootAccu5Day> call = IAccuWeatherApi.getDurban5Day();

        //.enqueue is a running a thread in background
        call.enqueue(new Callback<RootAccu5Day>() {
            @Override
            public void onResponse(Call<RootAccu5Day> call, Response<RootAccu5Day> response) {
                pb_cityDetails.setVisibility(View.GONE);

                if(!response.isSuccessful()){
                    tv_cityname.setText("Code: " + response.code());
                    return;
                }

                RootAccu5Day AccuDetailsObj = response.body();

                tv_cityname.append("Description: " + AccuDetailsObj.getHeadline().getText() + "\n\n");

                for(DailyForecast oneDay: AccuDetailsObj.getDailyForecasts()){
                    int i = 1;
                    tv_cityname.append(oneDay.date.toString() + i++ + " Max temp: " + oneDay.temperature.maximum.getValue() + "\n");
                }
            }

            @Override
            public void onFailure(Call<RootAccu5Day> call, Throwable t) {
                pb_cityDetails.setVisibility(View.GONE);
                tv_cityname.setText(t.getMessage());
            }
        });
    }

    private void retrofitBuildBase(){
        pb_cityDetails.setVisibility(View.VISIBLE);

        final String BASE_URL = "https://dataservice.accuweather.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Convert json to PoJo. Using GSON library(made by google).
                .build();

        oneIAccuWeatherApi = retrofit.create(IAccuWeatherApi.class);//Using instance of interface to...

        //getForecast();
        SearchCityApi();
    }

    private void SearchCityApi() {
//        String tempCitySearchText = sdf
        Call<List<RootAccuCitySearch>> onAccuCitySearch = oneIAccuWeatherApi.getCitySearch();

        onAccuCitySearch.enqueue(new Callback<List<RootAccuCitySearch>>() {
            @Override
            public void onResponse(Call<List<RootAccuCitySearch>> call, Response<List<RootAccuCitySearch>> response) {
                pb_cityDetails.setVisibility(View.GONE);

                if(!response.isSuccessful()){
                    tv_cityname.setText("response.isSuccessful: \n" + response.code());
                    return;
                }

                List<RootAccuCitySearch> citySearchObj = response.body();

                for(RootAccuCitySearch oneCity: citySearchObj){
                    String tempCityDetails = "";

                    tempCityDetails += "City rank: " + oneCity.getRank();
                    tempCityDetails += "\n\n City name: " +oneCity.getEnglishName();
                    tempCityDetails += "\n\n City key: " +oneCity.getKey();

                    tv_cityname.setText(tempCityDetails);
                }
            }

            @Override
            public void onFailure(Call<List<RootAccuCitySearch>> call, Throwable t) {
                pb_cityDetails.setVisibility(View.GONE);
                tv_cityname.setText(t.getMessage());
            }
        });
    }

    private void getForecast() {
        Call<List<RootAccuCitySearch>> call = oneIAccuWeatherApi.getCitySearch();//"Call" is retrofit class
        //call.enqueue is a thread running in the background, it's made by retrofit. It Needs to be in the background because it's a network operation and if it freezes the whole app does too???

        call.enqueue(new Callback<List<RootAccuCitySearch>>() {
            @Override
            public void onResponse(Call<List<RootAccuCitySearch>> call, Response<List<RootAccuCitySearch>> response) {
                pb_cityDetails.setVisibility(View.GONE);

                //response.isSuccessful ??? means response code is 200 to 300.
                if(!response.isSuccessful()){//If not successful then... This needs to be done because it could successfully respond 404 which is not good.
                    tv_cityname.setText("response.isSuccessful: \n " + response.code());
                    return;//Don't continue because data will be null.
                }

                //Tip: if you type response.body you will see that it return of type "List<RootAccuCitySearch>"
                List<RootAccuCitySearch> AccuDetailsObj = response.body();

                //how loop works: for("Data type you receive e.g int, string" -space- "temp object name": "source") {}
                for(RootAccuCitySearch oneCity: AccuDetailsObj){
                    tv_cityname.append("City rank: " + oneCity.getRank());
                    tv_cityname.append("\n\n City key: " +oneCity.getKey());
                    tv_cityname.append("\n\n City name: " +oneCity.getEnglishName());
                }

            }

            @Override
            public void onFailure(Call<List<RootAccuCitySearch>> call, Throwable t) {
                pb_cityDetails.setVisibility(View.GONE);
                tv_cityname.setText("OnFailure: \n" + t.getMessage());
            }
        });
    }
}