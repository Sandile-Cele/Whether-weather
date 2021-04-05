package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.AccuPojo.AccuCitySearchPojo.RootAccuCitySearch;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sandile.wheatherweather.AccuApi.ApiEngine;
import com.sandile.wheatherweather.AccuApi.IAccuWeatherApi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import PhoneOpertion.PhoneControl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_citysearch;
    private TextView tv_cityname;
    private EditText et_cityname;
    private FloatingActionButton fab_goto_forecastList, fab_getLocation, fab_screenshot;
    private ProgressBar pb_cityDetails;
    private IAccuWeatherApi oneIAccuWeatherApi;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing pallets
        btn_citysearch = findViewById(R.id.main_btn_search);
        btn_citysearch.setOnClickListener(this);

        tv_cityname = findViewById(R.id.main_tv_cityname);
        et_cityname = findViewById(R.id.main_et_citysearch);

        fab_goto_forecastList = findViewById(R.id.main_fab_gotoForecastList);
        fab_goto_forecastList.setOnClickListener(this);

        fab_getLocation = findViewById(R.id.main_fab_getLocation);
        fab_getLocation.setOnClickListener(this);

        fab_screenshot = findViewById(R.id.main_fab_screenshot);
        fab_screenshot.setOnClickListener(this);

        pb_cityDetails = findViewById(R.id.main_pb_cityDetails);
        //Done initializing pallets


        //When user starts app ask them to enable location!


//        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()// This code is to remove data sharing restrictions
//        StrictMode.setVmPolicy(builder.build())


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_search:
                PhoneControl.hideKeyboard(this);
                AccuCitySearch(getCityText(), new ApiEngine().RetrofitBuildBase());
                break;
            case R.id.main_fab_getLocation:
                setLocation();
                break;
            case R.id.main_fab_screenshot:
                takeScreenshotLogic();
                break;
            case R.id.main_fab_gotoForecastList:
//                finish();//This is to close activity
                if (isThereCityKey())
                    startActivity(new Intent(this, ForecastList.class));
                break;
        }
    }

    private void takeScreenshotLogic() {
        isStorageGranted(this);
        takeScreenshot(getWindow().getDecorView().getRootView(), "screenshot");
    }

    private Boolean isThereCityKey() {
        if (ApiEngine.tempCityKey == 0) {
            Toast.makeText(this, "Search for city or get current location.", Toast.LENGTH_LONG).show();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void setLocation() {
        if (LocationManager.SetCurrentLocation(this)) {
            LocationManager.restBool();
            AccuCitySearch(LocationManager.getLocationString(), new ApiEngine().RetrofitBuildBase());
        } else {
            Toast.makeText(this, "try again", Toast.LENGTH_LONG).show();
        }
    }

    private String getCityText() {
        return et_cityname.getText().toString().trim();
    }

    //Cheating using for location search + text.
    //This needs to move to Api engine But can't because "onResponse" method can't return strings which is needed to assign to text view.
    private void AccuCitySearch(String searchCityInput, IAccuWeatherApi oneIAccuObj) {//Static key set here
        pb_cityDetails.setVisibility(View.VISIBLE);

        if (searchCityInput.isEmpty()) {
            Toast.makeText(this, "City input data is empty" + searchCityInput, Toast.LENGTH_LONG).show();
            return;
        }

        Call<List<RootAccuCitySearch>> onAccuCitySearch = oneIAccuObj.getCitySearch(searchCityInput);

        onAccuCitySearch.enqueue(new Callback<List<RootAccuCitySearch>>() {
            @Override
            public void onResponse(Call<List<RootAccuCitySearch>> call, Response<List<RootAccuCitySearch>> response) {
                pb_cityDetails.setVisibility(View.GONE);

                if (!response.isSuccessful()) {
                    tv_cityname.setText("response.isSuccessful: \n" + response.code());
                    pb_cityDetails.setVisibility(View.GONE);
                    return;
                }

                List<RootAccuCitySearch> citySearchObj = response.body();


                for (RootAccuCitySearch oneCity : citySearchObj) {
                    ApiEngine.tempCityKey = oneCity.getKey();//Setting static key

                    String tempCityDetails = "";

                    tempCityDetails += "City rank: " + oneCity.getRank();
                    tempCityDetails += "\nCity name: " + oneCity.getEnglishName();
                    tempCityDetails += "\nCity key: " + oneCity.getKey();
                    tempCityDetails += "\nType: " + oneCity.getType();
                    tempCityDetails += "\nCity localized name: " + oneCity.getLocalizedName();

                    tv_cityname.setText(tempCityDetails);
                }
            }

            @Override
            public void onFailure(Call<List<RootAccuCitySearch>> call, Throwable t) {
                pb_cityDetails.setVisibility(View.GONE);
                tv_cityname.setText(t.getMessage());
            }
        });
    }

    private File takeScreenshot(View view, String fileName) {
        Date date = new Date();
        CharSequence dateFormatted = DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);

        try {
            String imgDir = Environment.getExternalStorageDirectory().toString() + "/Whether-weather";
            File fileDir = new File(imgDir);

            if (!fileDir.exists()) {
                if (!fileDir.mkdirs()) {
                    Toast.makeText(this, "Could not make file to store images.", Toast.LENGTH_LONG).show();
                }
            }

            String imgPath = imgDir + "/" + fileName + "-" + dateFormatted + ".jpeg";

            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(imgPath);


            FileOutputStream fileOutputStream = new FileOutputStream(fileDir);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            return imageFile;
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }

        return null;
    }

    public static void isStorageGranted(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void shareImageFromURI(Uri inImageToShare) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, inImageToShare);
        shareIntent.setType("image/jpeg");
//      startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
        startActivity(Intent.createChooser(shareIntent, "Share using"));

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //Check if user granted access to location
    }
}