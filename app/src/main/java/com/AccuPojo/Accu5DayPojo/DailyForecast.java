package com.AccuPojo.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DailyForecast{
    @SerializedName("Date")
    private Date date;
    @SerializedName("EpochDate")
    private int epochDate;
    @SerializedName("Temperature")
    private Temperature temperature;
    @SerializedName("Day")
    private Day day;
    @SerializedName("Night")
    private Night night;
    @SerializedName("Sources")
    private List<String> sources;
    @SerializedName("MobileLink")
    private String mobileLink;
    @SerializedName("Link")
    private String link;

    public Date getDate() {
        return date;
    }

    public int getEpochDate() {
        return epochDate;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Day getDay() {
        return day;
    }

    public Night getNight() {
        return night;
    }

    public List<String> getSources() {
        return sources;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public String getLink() {
        return link;
    }
}
