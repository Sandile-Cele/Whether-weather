package com.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

public class Temperature{
    @SerializedName("Minimum") 
    public Minimum minimum;
    @SerializedName("Maximum") 
    public Maximum maximum;
}
