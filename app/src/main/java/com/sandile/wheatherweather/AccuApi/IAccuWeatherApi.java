package com.sandile.wheatherweather.AccuApi;

import com.AccuPojo.Accu5DayPojo.RootAccu5Day;
import com.AccuPojo.AccuCitySearchPojo.RootAccuCitySearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Find out why this is an interface??
public interface IAccuWeatherApi {//Responsible for returning call object

//    @Headers("api-key: " + "PUT_YOUR_API_KEY")
    @GET("forecasts/v1/daily/5day/{cityKey}?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&metric=true")
    Call<RootAccu5Day> get5DayForecast(@Path("cityKey") int cityKey);//Tip name this what you are getting back
    //This is the Call object is of type "RootAccu5Day". And this the root of 5day forecast json from Accuweather

    @GET("locations/v1/cities/search?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY")
    Call<List<RootAccuCitySearch>> getCitySearch(@Query("q") String citySearchText);
    // ^ ^ ^  This call object is type "list" because the json is of type list. TIP because json starts with "[".


    //This is not in use, it is not needed BECAUSE text search also takes geoPosition
    @GET("locations/v1/cities/geoposition/search?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&")
    Call<RootAccuCitySearch> getCitySearchGeo(@Query("q") String citySearchGeo);//q=26.0932%2C%2028.0471

}
