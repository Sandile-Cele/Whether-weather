package com.sandile.wheatherweather;

import com.google.gson.annotations.SerializedName;

public class AccuGetCityDetails {
    @SerializedName("Key")
    private String key;
    @SerializedName("Type")
    private String type;
    @SerializedName("Rank")
    private Integer rank;

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public Integer getRank() {
        return rank;
    }
}
