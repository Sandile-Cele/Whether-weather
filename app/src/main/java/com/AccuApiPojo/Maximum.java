package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

public class Maximum{
    @SerializedName("Value")
    private double value;
    @SerializedName("Unit")
    private String unit;
    @SerializedName("UnitType")
    private int unitType;

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
