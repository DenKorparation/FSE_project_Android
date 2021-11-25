package com.uni;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static com.uni.MainActivity.database;

public class ScrollingActivity extends AppCompatActivity {

    private Button temp;
    private Button prec;
    private Button clouds;
    private Button Windspeed;
    private Button pressurem;
    private ImageView weathermap;
    private ImageView imageView8;
    private Button zoom1;
    private Button zoomminus;
    private TextView textview6;
    private TextView textView3;
    private ImageView imageView4;
    private ImageView imageView3;
    private ImageView imageView1;
    public static TextView result_info;
    public static TextView feelslike;
    public static TextView windspeed;
    public static TextView pressure;
    public static ImageView icon;
    public static String idIcons;
    public static Map<String, Integer> map = new HashMap<String, Integer>();
    public static weatherRVAdapter weatherRvAdapter;
    public static weatherRVAdapter2 weatherRVAdapter2;
    public static LinearLayoutManager layoutManager;
    public static LinearLayoutManager layoutManager2;
    public static RecyclerView weatherlist;
    public static RecyclerView weatherlist2;
    private Button button;
    private EditText textView2;
    private ImageView imageView2;
    private TextView textView4;
    private TextView textView5;
    private ImageView legend;
    //  private ActivityScrollingBinding binding;
    private int id;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        legend = findViewById(R.id.imageView4);
        pressurem = findViewById(R.id.button2);
        temp = findViewById(R.id.button4);
        prec = findViewById(R.id.button6);
        Windspeed = findViewById(R.id.button5);
        clouds = findViewById(R.id.button3);
        weathermap = findViewById(R.id.map2);
        imageView8=findViewById(R.id.map1);
        zoom1 = findViewById(R.id.button8);
        zoomminus = findViewById(R.id.button9);
        textview6 = findViewById(R.id.textView6);
        textView5 = findViewById(R.id.textView5);
        textView3 = findViewById(R.id.textView3);
        imageView4 = findViewById(R.id.imageView11);
        imageView2 = findViewById(R.id.imageView9);
        imageView1 = findViewById(R.id.imageView8);
        weatherlist2 = findViewById(R.id.rv_blocks1);
        weatherlist = findViewById(R.id.rv_blocks);
        result_info = findViewById(R.id.resultinfo);
        feelslike = findViewById(R.id.feelslike);
        windspeed = findViewById(R.id.windspeed);
        pressure = findViewById(R.id.pressure);
        icon = findViewById(R.id.icon);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        imageView3 = findViewById(R.id.imageView10);
        layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        textView4 = findViewById(R.id.textView4);
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

        new ScrollingActivity.Request().execute();

        Windspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setMapLayer("wind_new");
                legend.setImageResource(R.drawable.wind_new);
                new Request().execute();


            }
        });
        pressurem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setMapLayer("pressure_new");
                legend.setImageResource(R.drawable.pressure_new);
                new Request().execute();

            }
        });
        clouds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setMapLayer("clouds_new");

                new Request().execute();


            }
        });
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setMapLayer("temp_new");
                legend.setImageResource(R.drawable.temp_new);
                new Request().execute();


            }
        });
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.setMapLayer("precipitation_new");
                legend.setImageResource(R.drawable.precipitation_new);
                new Request().execute();


            }
        });
        zoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.zoomIncrement();
                new Request().execute();


            }
        });
        zoomminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.zoomDecrement();
                new Request().execute();


            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (textView2.getText().toString().trim().equals("")) {
                    Toast.makeText(ScrollingActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                }
                else {
                    database.setNameOfCity(textView2.getText().toString().trim());
                    new Request().execute();

                    //icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                }


                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }


    private class Request extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            textView4.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            database.request();
            return null;
        }

        //@Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (database.isCorrectData()) {
                weatherlist.setLayoutManager(layoutManager);
                weatherlist.setHasFixedSize(true);
                weatherRvAdapter = new weatherRVAdapter(48);
                weatherlist.setAdapter(weatherRvAdapter);
                weatherRvAdapter.notifyItemChanged(0, 47);
                textView3.setText("Почасовой прогноз");
                imageView4.setImageResource(R.drawable.ic_thermometer_temperature_svgrepo_com);
                weatherlist2.setLayoutManager(layoutManager2);
                textview6.setText("Ощущается как");
                weatherlist2.setHasFixedSize(true);
                weatherRVAdapter2 = new weatherRVAdapter2(7);
                weatherlist2.setAdapter(weatherRVAdapter2);
                weatherRVAdapter2.notifyItemChanged(0,6);
                imageView2.setImageResource(R.drawable.windspeed);
                ScrollingActivity.result_info.setText(Float.toString(database.getCurWeatherData().getTemp()) + "°C");
                feelslike.setText(Float.toString(database.getCurWeatherData().getFeelsLikeTemp()) + "°C");
                windspeed.setText(Float.toString(database.getCurWeatherData().getWindSpeed()) + " m/s");
                pressure.setText(Float.toString(database.getCurWeatherData().getPressure()) + " HPa");
                icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                textView4.setText(database.getNameOfCity());
                imageView3.setImageResource(R.drawable.ic_temp);
                imageView1.setImageResource(R.drawable.pressure);
                textView5.setText("Ежедневный прогноз");
                Picasso.get().load(database.getMap()).into(imageView8);
                Picasso.get().load(database.getWeatherMap()).into(weathermap);

            } else
                textView4.setText("Incorrect data");

        }




    }
}