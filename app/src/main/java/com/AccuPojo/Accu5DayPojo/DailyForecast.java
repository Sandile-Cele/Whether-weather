package com.AccuPojo.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public Calendar getDate() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
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
