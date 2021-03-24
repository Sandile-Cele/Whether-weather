package com.sandile.wheatherweather.AccuApi;

import com.AccuPojo.AccuCitySearchPojo.RootAccuCitySearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiEngine {//Using Retrofit
    private IAccuWeatherApi oneIAccuWeatherApi;

    public static int tempCityKey = 2884280;

    public IAccuWeatherApi RetrofitBuildBase(){
        final String BASE_URL = "https://dataservice.accuweather.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//Convert json to PoJo. Using GSON library(made by google).
                .build();

        return retrofit.create(IAccuWeatherApi.class);//Using instance of interface to...
    }

    //Must add AccuCitySearch, found in main
    //Must add Accu5Day, found in ForecastList, but impossible because can't return data in onResponse
}
