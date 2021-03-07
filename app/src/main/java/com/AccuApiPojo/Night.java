package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

public class Night{
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
