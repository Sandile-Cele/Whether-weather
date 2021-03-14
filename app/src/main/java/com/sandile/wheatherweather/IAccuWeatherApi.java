package com.sandile.wheatherweather;

import com.Accu5DayPojo.RootAccu5Day;
import com.AccuCitySearchPojo.RootAccuCitySearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAccuWeatherApi {

//    @Headers("api-key: " + "PUT_YOUR_API_KEY")
    @GET("forecasts/v1/daily/5day/305605?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&metric=true")
    Call<RootAccu5Day> getDurban5Day();

    @GET("locations/v1/cities/search?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&q=Richards%20Bay")
    Call<List<RootAccuCitySearch>> getCitySearch();
}
