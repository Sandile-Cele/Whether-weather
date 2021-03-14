package com.sandile.wheatherweather;

import com.Accu5DayPojo.RootAccu5Day;
import com.AccuCitySearchPojo.RootAccuCitySearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Find out why this is an interface??
public interface IAccuWeatherApi {//Responsible for returning call object

//    @Headers("api-key: " + "PUT_YOUR_API_KEY")
    @GET("forecasts/v1/daily/5day/305605?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&metric=true")
    Call<RootAccu5Day> getDurban5Day();//Tip name this what you are getting back
    //This is the Call object is of type "RootAccu5Day". And this the root of 5day forecast json from Accuweather

    @GET("locations/v1/cities/search?apikey=xkiA0fqNBMqMLSc2dHK8Q8aIwGjWnBAY&q=Durban")
    Call<List<RootAccuCitySearch>> getCitySearch();//@Path("qCitySearch") String citySearchText
    // ^ ^ ^  This call object is type "list" because the json is of type list. TIP because json starts with "[".
}
