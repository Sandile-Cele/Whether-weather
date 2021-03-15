package com.AccuPojo.AccuCitySearchPojo;

import com.google.gson.annotations.SerializedName;

 public class RootAccuCitySearch {

     //     CLASS NOT COMPLETE, MANY CLASSES ARE MISSING

    @SerializedName("Version")
    private
    int version;
    @SerializedName("Key")
    private
    int key;
    @SerializedName("Type")
    private
    String type;
    @SerializedName("Rank")
    private
    int rank;
    @SerializedName("LocalizedName")
    private
    String localizedName;
    @SerializedName("EnglishName")
    private
    String englishName;
    @SerializedName("PrimaryPostalCode")
    private
    String primaryPostalCode;

     public int getVersion() {
         return version;
     }

     public int getKey() {
         return key;
     }

     public String getType() {
         return type;
     }

     public int getRank() {
         return rank;
     }

     public String getLocalizedName() {
         return localizedName;
     }

     public String getEnglishName() {
         return englishName;
     }

     public String getPrimaryPostalCode() {
         return primaryPostalCode;
     }
 }
