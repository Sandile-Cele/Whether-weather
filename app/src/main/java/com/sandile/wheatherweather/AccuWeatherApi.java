package com.sandile.wheatherweather;

import com.AccuApiPojo.DailyForecast;
import com.AccuApiPojo.RootAccu5Day;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccuWeatherApi {

//    @Headers("api-key: " + "PUT_YOUR_API_KEY")
    @GET("forecasts/v1/daily/5day/305605?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&metric=true")
    Call<RootAccu5Day> getPosts();
}
