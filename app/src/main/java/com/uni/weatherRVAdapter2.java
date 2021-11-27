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
import java.util.TimeZone;

import static com.uni.MainActivity.database;

/**
 * @author Vlad and Denis
 * @version 1.0
 * Daily Adapter
 */
public class weatherRVAdapter2 extends RecyclerView.Adapter<weatherRVAdapter2.WeatherViewHolder>{

    /**
     * ViewHolderCount
     */
    private static int viewHolderCount;
    /**
     * Amount of blocks
     */
    private int numberItems;

    /**
     * @param numberofItems daily adapter
     */
    public weatherRVAdapter2(int numberofItems){
        numberItems = numberofItems;
        viewHolderCount = 0;
    }

    /**
     * Creating daily holder
     */
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_rv_item2;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent,false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        viewHolderCount ++;
        return viewHolder;

    }

    /**
     * @param holder right holder for info
     * @param position right position for info
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(position);
    }

    /**
     * @return Number of elements
     */
    @Override
    public int getItemCount() {
        return numberItems;
    }

    /**
     * Daily Recycler View
     */
    class WeatherViewHolder extends RecyclerView.ViewHolder {
        /**
         * Daily day temperature
         */
        TextView tempday;
        /**
         * Daily time
         */
        TextView time;
        /**
         * Daily icon of the weather
         */
        ImageView icon;
        /**
         * Daily wind speed
         */
        TextView speed;
        /**
         * Daily night temperature
         */
        TextView tempnight;

        /**
         * @param itemView Daily holder
         */
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tempday = itemView.findViewById(R.id.idTVTemperatureday);
            tempnight = itemView.findViewById(R.id.idTVTemperaturenignt);
            time = itemView.findViewById(R.id.idTVTime);
            icon = itemView.findViewById(R.id.idIVCondition);
            speed = itemView.findViewById(R.id.idTVWindSpeed);

        }

        /**
         * @param listIndex Putting info in parametrs
         */
        void bind(int listIndex) {
            tempday.setText(Float.toString(database.getDailyForecast()[listIndex].getTempDay())+ "°C");
            tempnight.setText(Float.toString(database.getDailyForecast()[listIndex].getTempNight())+"°C");
            icon.setImageResource(ScrollingActivity.map.get(database.getDailyForecast()[listIndex].getIdIcon()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd\nMMMM"); // какой формат нужен, выбираем
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
            time.setText(sdf.format(database.getDailyForecast()[listIndex].getTime()));
            speed.setText(Float.toString(database.getDailyForecast()[listIndex].getWindSpeed())+" m/s");
        }
    }
}
