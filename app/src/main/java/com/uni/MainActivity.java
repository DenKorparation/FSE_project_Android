package com.uni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private RecyclerView weatherlist;
    private Database database;
    private EditText user_field;
    private Button main_btn;
    private TextView result_info;
    private TextView feelslike;
    private TextView windspeed;
    private TextView pressure;
    private ImageView icon;
    public String idIcons;
    private Map<String, Integer> map = new HashMap<String, Integer>();
    private weatherRVAdapter weatherRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database();
        weatherlist = findViewById(R.id.rv_blocks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        weatherlist.setLayoutManager(layoutManager);
        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_button);
        result_info = findViewById(R.id.resultinfo);
        feelslike = findViewById(R.id.feelslike);
        windspeed = findViewById(R.id.windspeed);
        pressure = findViewById(R.id.pressure);
        icon = findViewById(R.id.icon);
        weatherRvAdapter = new weatherRVAdapter(10);
        weatherlist.setAdapter(weatherRvAdapter);


        map.put("01d", R.drawable.ic__1d);
        map.put("01n", R.drawable.ic__1n);
        map.put("02d", R.drawable.ic__2d);
        map.put("02n", R.drawable.ic__2n);
        map.put("03d", R.drawable.ic__3d);
        map.put("03n", R.drawable.ic__3n);
        map.put("04d", R.drawable.ic__4d);
        map.put("04n", R.drawable.ic__4n);
        map.put("09d", R.drawable.ic__9d);
        map.put("09n", R.drawable.ic__9n);
        map.put("10d", R.drawable.ic__10d);
        map.put("10n", R.drawable.ic__10n);
        map.put("11d", R.drawable.ic__11d);
        map.put("11n", R.drawable.ic__11n);
        map.put("13d", R.drawable.ic__13d);
        map.put("13n", R.drawable.ic__13n);
        map.put("50d", R.drawable.ic__50d);
        map.put("50n", R.drawable.ic__50n);





        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                else{
                    database.setNameOfCity(user_field.getText().toString().trim());
                    new Request().execute();
                    //System.out.println("|" + user_field.getText().toString().trim() + "|  " + database.getCurWeatherData().getTemp());
                    //icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                }
            }
        });
    }
    private class Request extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            result_info.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            database.request();
            return null;
        }

        //@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (database.isCorrectData())
            {result_info.setText(database.getNameOfCity() + "\n" + Float.toString(database.getCurWeatherData().getTemp())+"°C");
                feelslike.setText( "Ощущается как: "+Float.toString(database.getCurWeatherData().getFeelsLikeTemp())+"°C");
                windspeed.setText(Float.toString(database.getCurWeatherData().getWindSpeed())+" m/s");
                pressure.setText(Float.toString(database.getCurWeatherData().getPressure())+" GPa");
                icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                }

            else
                result_info.setText("Incorrect data");

        }

        }

}