package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class DailyForecast{
    @SerializedName("Date")
    public Date date;
    @SerializedName("EpochDate")
    public int epochDate;
    @SerializedName("Temperature")
    public Temperature temperature;
    @SerializedName("Day")
    public Day day;
    @SerializedName("Night")
    public Night night;
    @SerializedName("Sources")
    public List<String> sources;
    @SerializedName("MobileLink")
    public String mobileLink;
    @SerializedName("Link")
    public String link;
}
