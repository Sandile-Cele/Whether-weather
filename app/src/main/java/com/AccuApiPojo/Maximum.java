package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

public class Maximum{
    @SerializedName("Value") 
    public double value;
    @SerializedName("Unit") 
    public String unit;
    @SerializedName("UnitType") 
    public int unitType;
}
