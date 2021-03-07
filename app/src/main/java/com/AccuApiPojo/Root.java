package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root{
    @SerializedName("Headline")
    public Headline headline;
    @SerializedName("DailyForecasts")
    public List<DailyForecast> dailyForecasts;
}
