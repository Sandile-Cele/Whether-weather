package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.AccuPojo.AccuCitySearchPojo.RootAccuCitySearch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import PhoneOpertion.PhoneControl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        fab_goto_forecastList = findViewById(R.id.main_fab_goto_forecastList);
        fab_goto_forecastList.setOnClickListener(this);

        pb_cityDetails = findViewById(R.id.main_pb_cityDetails);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_btn_search:
                PhoneControl.hideKeyboard(this);
                SearchCityApi(AccuCityTextBuilder(), new ApiEngine().RetrofitBuildBase());
                break;
            case R.id.main_fab_goto_forecastList:
//                finish();//This is to close activity
                startActivity(new Intent(this, ForecastList.class));
                break;
        }
    }

    private String AccuCityTextBuilder() {
        return et_cityname.getText().toString().trim();
    }

    private void SearchCityApi(String citySearch, IAccuWeatherApi oneIAccuObj) {//Static key set here
        pb_cityDetails.setVisibility(View.VISIBLE);
        Call<List<RootAccuCitySearch>> onAccuCitySearch = oneIAccuObj.getCitySearch(citySearch);

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
                    ApiEngine.tempCityKey = oneCity.getKey();//Setting static key

                    String tempCityDetails = "";

                    tempCityDetails += "City rank: " + oneCity.getRank();
                    tempCityDetails += "\n\nCity name: " +oneCity.getEnglishName();
                    tempCityDetails += "\n\nCity key: " +oneCity.getKey();

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

}