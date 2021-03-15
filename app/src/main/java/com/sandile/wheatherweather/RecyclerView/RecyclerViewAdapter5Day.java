package com.sandile.wheatherweather.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandile.wheatherweather.R;

import java.util.ArrayList;

public class RecyclerViewAdapter5Day extends RecyclerView.Adapter<RecyclerViewAdapter5Day.RecyclerViewHolder> {
    private ArrayList<SingleForecastItem> singleForecastItemObj;

            //Find out what this class does
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.forecastItem_iv_viewImage);
            textView1 = itemView.findViewById(R.id.forecastItem_tv_line1);
            textView2 = itemView.findViewById(R.id.forecastItem_tv_line2);
        }
    }

    //???Getting data out of arrayList in "RecyclerViewHolder" to adapter"RecyclerViewAdapter5Day". //???This is done by passing it to the constructor  of "RecyclerViewAdapter5Day"
    public RecyclerViewAdapter5Day(ArrayList<SingleForecastItem> forecast5DayList){
        singleForecastItemObj = forecast5DayList;
    }

    @NonNull
    @Override//????Something about assigning "static class RecyclerViewHolder" to views
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //"activity_forecast_list" is the xml for Recycler view
        View viewObj = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_forecast_list, parent, false);

        //???I think "activity_forecast_list" is being passed to RecyclerViewHolder
        RecyclerViewHolder recyclerViewHolderObj = new RecyclerViewHolder(viewObj);

        return recyclerViewHolderObj;
    }

    //...first item in rv will be 0 in "int position"(below)
    //then if you are at position 0 you have to get data in position 0 in "singleForecastItemObj"(Very top) and slap it in the RV index 0
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SingleForecastItem currentItem = singleForecastItemObj.get(position);

        //"getImageResource()" getter method in "SingleForecastItem>getImageResource()" class I created
        //So basically take data in "singleForecastItemObj"(Which is populated) the shove it in the RV
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());

    }

    //How many items are there going to be in list ??RV list(total)
    @Override
    public int getItemCount() {
        return singleForecastItemObj.size();
    }
}
