package com.sandile.wheatherweather.AccuApi;

import com.AccuPojo.AccuCitySearchPojo.RootAccuCitySearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiEngine {
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

    private void SearchCityApi(String citySearch) {
        Call<List<RootAccuCitySearch>> oneAccuCitySearch = oneIAccuWeatherApi.getCitySearch(citySearch);


        oneAccuCitySearch.enqueue(new Callback<List<RootAccuCitySearch>>() {
            @Override
            public void onResponse(Call<List<RootAccuCitySearch>> call, Response<List<RootAccuCitySearch>> response) {

                if(!response.isSuccessful()){
                    return;
                }


//                for(RootAccuCitySearch oneCity: citySearchObj){
//                    String tempCityDetails = "";
//
//                    tempCityDetails += "City rank: " + oneCity.getRank();
//                    tempCityDetails += "\n\nCity name: " +oneCity.getEnglishName();
//                    tempCityDetails += "\n\nCity key: " +oneCity.getKey();
//
//                    tv_cityname.setText(tempCityDetails);
//                }
            }

            @Override
            public void onFailure(Call<List<RootAccuCitySearch>> call, Throwable t) {
//                pb_cityDetails.setVisibility(View.GONE);
////                tv_cityname.setText(t.getMessage());
            }
        });
    }
}
