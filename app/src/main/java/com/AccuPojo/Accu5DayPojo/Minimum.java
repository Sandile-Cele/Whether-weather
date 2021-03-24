package com.AccuPojo.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

public class Minimum{
    @SerializedName("Value") 
    public double value;
    @SerializedName("Unit") 
    public String unit;
    @SerializedName("UnitType") 
    public int unitType;

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public int getUnitType() {
        return unitType;
    }
}
