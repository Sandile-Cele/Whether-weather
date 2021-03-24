package com.AccuPojo.Accu5DayPojo;

import com.google.gson.annotations.SerializedName;

public class Day{
    @SerializedName("Icon")
    public int icon;
    @SerializedName("IconPhrase") 
    public String iconPhrase;
    @SerializedName("HasPrecipitation") 
    public boolean hasPrecipitation;
    @SerializedName("PrecipitationType") 
    public String precipitationType;
    @SerializedName("PrecipitationIntensity") 
    public String precipitationIntensity;
}
