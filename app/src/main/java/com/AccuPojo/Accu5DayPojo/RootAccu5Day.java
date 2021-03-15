package com.AccuPojo.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootAccu5Day {
    @SerializedName("Headline")
    private Headline headline;
    @SerializedName("DailyForecasts")
    private List<DailyForecast> dailyForecasts;

    public Headline getHeadline() {
        return headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }


}
