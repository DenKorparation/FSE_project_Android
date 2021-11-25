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

public class weatherRVAdapter2 extends RecyclerView.Adapter<weatherRVAdapter2.WeatherViewHolder>{

    private static int viewHolderCount;
    private int numberItems;
    public weatherRVAdapter2(int numberofItems){
        numberItems = numberofItems;
        viewHolderCount = 0;
    }
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

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tempday;
        TextView time;
        ImageView icon;
        TextView speed;
        TextView tempnight;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tempday = itemView.findViewById(R.id.idTVTemperatureday);
            tempnight = itemView.findViewById(R.id.idTVTemperaturenignt);
            time = itemView.findViewById(R.id.idTVTime);
            icon = itemView.findViewById(R.id.idIVCondition);
            speed = itemView.findViewById(R.id.idTVWindSpeed);

        }

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
