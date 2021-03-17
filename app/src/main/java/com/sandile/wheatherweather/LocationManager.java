package com.sandile.wheatherweather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationManager {
    private static FusedLocationProviderClient fusedLocationClient;//Location thing
    private static final int COARSE_LOCATION_REQUEST_CODE = 0;
    public static double longitude, latitude;
    public static Boolean isLocSet = false;

    public static void restBool(){
        isLocSet = null;
    };

    public static String getLocationString(){
        return latitude + ", " + longitude;
    }

    public static Boolean SetCurrentLocation(Activity inCurrentActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(inCurrentActivity);

        if (ContextCompat.checkSelfPermission(inCurrentActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                                                                                                    // TODO: Consider calling
                                                                                                    //    ActivityCompat#requestPermissions
                                                                                                    // here to request the missing permissions, and then overriding
                                                                                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
                                                                                                    // to handle the case where the user grants the permission. See the documentation// for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(inCurrentActivity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE_LOCATION_REQUEST_CODE);

        }
        else{
            fusedLocationClient.getLastLocation()
                    .addOnCompleteListener(inCurrentActivity, task -> {
                        if (task != null) {
                            latitude = task.getResult().getLatitude();
                            longitude = task.getResult().getLongitude();

                            Log.d("LocationManager", "Location lat:" + latitude);
                            Log.d("LocationManager", "location long:" + longitude);

                            isLocSet = true;
                        }
                        else {
                            Toast.makeText(inCurrentActivity, "Location not changed! \nLocation can't be found!", Toast.LENGTH_LONG).show();
                            isLocSet = false;
                        }
                    });
        }
        return isLocSet;
    }



}

