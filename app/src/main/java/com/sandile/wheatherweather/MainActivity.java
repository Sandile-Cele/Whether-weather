package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
                takeScreenshot();
                break;
            case R.id.main_fab_gotoForecastList:
//                finish();//This is to close activity
                if(isThereCityKey())
                    startActivity(new Intent(this, ForecastList.class));
                break;
        }
    }

    private Boolean isThereCityKey(){
        if(ApiEngine.tempCityKey == 0){
            Toast.makeText(this,"Search for city or get current location.",Toast.LENGTH_LONG).show();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void setLocation(){
        if(LocationManager.SetCurrentLocation(this)){
            LocationManager.restBool();
            AccuCitySearch(LocationManager.getLocationString(), new ApiEngine().RetrofitBuildBase());
        }
        else{
            Toast.makeText(this,"try again",Toast.LENGTH_LONG).show();
        }
    }

    private String getCityText() {
        return et_cityname.getText().toString().trim();
    }

    //Cheating using for location search + text.
    //This needs to move to Api engine But can't because "onResponse" method can't return strings which is needed to assign to text view.
    private void AccuCitySearch(String searchCityInput, IAccuWeatherApi oneIAccuObj) {//Static key set here
        pb_cityDetails.setVisibility(View.VISIBLE);

        if(searchCityInput.isEmpty()){
            Toast.makeText(this,"City input data is empty" + searchCityInput,Toast.LENGTH_LONG).show();
            return;
        }

        Call<List<RootAccuCitySearch>> onAccuCitySearch = oneIAccuObj.getCitySearch(searchCityInput);

        onAccuCitySearch.enqueue(new  Callback<List<RootAccuCitySearch>>() {
            @Override
            public void onResponse(Call<List<RootAccuCitySearch>> call, Response<List<RootAccuCitySearch>> response) {
                pb_cityDetails.setVisibility(View.GONE);

                if(!response.isSuccessful()){
                    tv_cityname.setText("response.isSuccessful: \n" + response.code());
                    pb_cityDetails.setVisibility(View.GONE);
                    return;
                }

                List<RootAccuCitySearch> citySearchObj = response.body();


                for(RootAccuCitySearch oneCity: citySearchObj){
                    ApiEngine.tempCityKey = oneCity.getKey();//Setting static key

                    String tempCityDetails = "";

                    tempCityDetails += "City rank: " + oneCity.getRank();
                    tempCityDetails += "\nCity name: " +oneCity.getEnglishName();
                    tempCityDetails += "\nCity key: " +oneCity.getKey();
                    tempCityDetails += "\nType: " +oneCity.getType();
                    tempCityDetails += "\nCity localized name: " +oneCity.getLocalizedName();

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

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path to include sd card appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

//            File imageFile = new File(mPath);

//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();

        }
        catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        //Check if user granted access to location
    }
}