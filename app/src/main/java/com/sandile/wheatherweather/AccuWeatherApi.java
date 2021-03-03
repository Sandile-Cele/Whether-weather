package com.sandile.wheatherweather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccuWeatherApi {

    @GET("")
    Call<List<AccuGetCityDetails>> getPosts();
}
