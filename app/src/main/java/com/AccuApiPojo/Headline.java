package com.AccuApiPojo;
import com.google.gson.annotations.SerializedName;

public class Headline{
    @SerializedName("EffectiveDate") 
    public Date effectiveDate;
    @SerializedName("EffectiveEpochDate") 
    public int effectiveEpochDate;
    @SerializedName("Severity") 
    public int severity;
    @SerializedName("Text") 
    public String text;
    @SerializedName("Category") 
    public String category;
    @SerializedName("EndDate") 
    public Date endDate;
    @SerializedName("EndEpochDate") 
    public int endEpochDate;
    @SerializedName("MobileLink") 
    public String mobileLink;
    @SerializedName("Link") 
    public String link;
}
