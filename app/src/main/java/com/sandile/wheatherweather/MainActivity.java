package com.sandile.wheatherweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_citysearch;
    private TextView tv_cityname;
    private EditText et_cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_citysearch = findViewById(R.id.main_btn_search);
        btn_citysearch.setOnClickListener(this);

        tv_cityname = findViewById(R.id.main_tv_cityname);
        et_cityname = findViewById(R.id.main_et_citysearch);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.main_btn_search:
                SearchCity();
//            case R.id.main_btn_daily_weight:
//                startActivity(new Intent(this, WeightLog.class));
//                break;
//            case R.id.main_btn_meal_log:
//                startActivity(new Intent(this, MealLog.class));
                break;
        }
    }

    private void SearchCity() {
        String CityName = et_cityname.getText().toString().trim();
        tv_cityname.setText(et_cityname.getText());
    }


}