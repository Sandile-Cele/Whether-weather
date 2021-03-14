package com.Accu5DayPojo;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Headline{
    @SerializedName("EffectiveDate")
    private String effectiveDate;
    @SerializedName("EffectiveEpochDate") 
    private int effectiveEpochDate;
    @SerializedName("Severity") 
    private int severity;
    @SerializedName("Text") 
    private String text;
    @SerializedName("Category") 
    private String category;
    @SerializedName("EndDate") 
    private Date endDate;
    @SerializedName("EndEpochDate") 
    private int endEpochDate;
    @SerializedName("MobileLink") 
    private String mobileLink;
    @SerializedName("Link") 
    private String link;

    public Headline() {
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public int getEffectiveEpochDate() {
        return effectiveEpochDate;
    }

    public int getSeverity() {
        return severity;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getEndEpochDate() {
        return endEpochDate;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public String getLink() {
        return link;
    }
}
