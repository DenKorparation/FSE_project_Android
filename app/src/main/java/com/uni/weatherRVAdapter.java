package com.uni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;


import static com.uni.MainActivity.database;

/**
 * @author Vlad and Denis
 * @version 1.0
 * Hourly recyclerview
 */
public class weatherRVAdapter extends RecyclerView.Adapter<weatherRVAdapter.WeatherViewHolder>{

    /**
     *viewHolderCount
     */
    private static int viewHolderCount;
    /**
     * Amount of blocks
     */
    private int numberItems;

    /**
     * @param numberofItems hourly Adapter
     */
    public weatherRVAdapter(int numberofItems){
        numberItems = numberofItems;
        viewHolderCount = 0;
    }

    /**
     * Making Hourly holder
     */
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_rv_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent,false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        viewHolderCount ++;
        return viewHolder;

    }

    /**
     * @param holder right holder for info
     * @param position right position of info
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(position);
    }

    /**
     * Number of elements
     */
    @Override
    public int getItemCount() {
        return numberItems;
    }

    /**
     * Recycler View
     */
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        /**
         * Hourly temperature
         */
        TextView temp;
        /**
         * Hourly time
         */
        TextView time;
        /**
         * Hourly icon of the weather
         */
        ImageView icon;
        /**
         * Hourly wind speed
         */
        TextView speed;

        /**
         * @param itemView Horly holder
         */
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            temp = itemView.findViewById(R.id.idTVTemperature);
            time = itemView.findViewById(R.id.idTVTime);
            icon = itemView.findViewById(R.id.idIVCondition);
            speed = itemView.findViewById(R.id.idTVWindSpeed);

        }

        /**
         * Putting info in hourly blocks
         */
        void bind(int listIndex) {
            temp.setText(Float.toString(database.getHourlyForecast()[listIndex].getTemp())+ "°C");
            icon.setImageResource(ScrollingActivity.map.get(database.getHourlyForecast()[listIndex].getIdIcon()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM\n HH:mm z"); // какой формат нужен, выбераем
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
            time.setText(sdf.format(database.getHourlyForecast()[listIndex].getTime()));
            speed.setText(Float.toString(database.getHourlyForecast()[listIndex].getWindSpeed())+" m/s");
        }
    }
}
