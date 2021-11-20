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

    private static int viewHolderCount2;
    private int numberItems2;
    public weatherRVAdapter2(int numberofItems2){
        numberItems2 = numberofItems2;
        viewHolderCount2 = 0;
    }
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_rv_item2;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent,false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        viewHolderCount2 ++;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems2;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView temp2;
        TextView time2;
        ImageView icon2;
        TextView speed2;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            temp2 = itemView.findViewById(R.id.idTVTemperature2);
            time2 = itemView.findViewById(R.id.idTVTime2);
            icon2 = itemView.findViewById(R.id.idIVCondition2);
            speed2 = itemView.findViewById(R.id.idTVWindSpeed2);

        }

        void bind(int listIndex) {
            temp2.setText(Float.toString(database.getHourlyForecast()[listIndex].getTemp()));
            icon2.setImageResource(ScrollingActivity.map.get(database.getHourlyForecast()[listIndex].getIdIcon()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM\n HH:mm z"); // какой формат нужен, выбераем
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
            time2.setText(sdf.format(database.getHourlyForecast()[listIndex].getTime()));
            speed2.setText(Float.toString(database.getHourlyForecast()[listIndex].getWindSpeed()));
        }
    }
}
