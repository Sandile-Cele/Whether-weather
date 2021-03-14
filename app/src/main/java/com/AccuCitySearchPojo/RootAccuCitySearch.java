package com.AccuCitySearchPojo;

import com.google.gson.annotations.SerializedName;

 public class RootAccuCitySearch {

     //     CLASS NOT COMPLETE, MANY CLASSES ARE MISSING

    @SerializedName("Version")
    private
    int version;
    @SerializedName("Key")
    private
    String key;
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

     public String getKey() {
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

     public void setVersion(int version) {
         this.version = version;
     }

     public void setKey(String key) {
         this.key = key;
     }

     public void setType(String type) {
         this.type = type;
     }

     public void setRank(int rank) {
         this.rank = rank;
     }

     public void setLocalizedName(String localizedName) {
         this.localizedName = localizedName;
     }

     public void setEnglishName(String englishName) {
         this.englishName = englishName;
     }

     public void setPrimaryPostalCode(String primaryPostalCode) {
         this.primaryPostalCode = primaryPostalCode;
     }
 }
