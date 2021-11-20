package com.uni;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

import java.util.HashMap;
import java.util.Map;

import static com.uni.MainActivity.database;

public class ScrollingActivity extends AppCompatActivity {

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
    private TextView textView4;
  //  private ActivityScrollingBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        weatherlist2 = findViewById(R.id.rv_blocks1);
        weatherlist = findViewById(R.id.rv_blocks);
        result_info = findViewById(R.id.resultinfo);
        feelslike = findViewById(R.id.feelslike);
        windspeed = findViewById(R.id.windspeed);
        pressure = findViewById(R.id.pressure);
        icon = findViewById(R.id.icon);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
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
            if (database.isCorrectData()) {
                weatherlist.setLayoutManager(layoutManager);
                weatherlist.setHasFixedSize(true);
                weatherRvAdapter = new weatherRVAdapter(48);
                weatherlist.setAdapter(weatherRvAdapter);
                weatherRvAdapter.notifyItemChanged(0, 47);


                weatherlist2.setLayoutManager(layoutManager2);
                weatherlist2.setHasFixedSize(true);
                weatherRVAdapter2 = new weatherRVAdapter2(7);
                weatherlist2.setAdapter(weatherRVAdapter2);
                weatherRVAdapter2.notifyItemChanged(0,6);

                ScrollingActivity.result_info.setText(Float.toString(database.getCurWeatherData().getTemp()) + "°C");
                feelslike.setText(Float.toString(database.getCurWeatherData().getFeelsLikeTemp()) + "°C");
                windspeed.setText(Float.toString(database.getCurWeatherData().getWindSpeed()) + " m/s");
                pressure.setText(Float.toString(database.getCurWeatherData().getPressure()) + " GPa");
                icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                textView4.setText(database.getNameOfCity());

            } else
                result_info.setText("Incorrect data");

        }


    }
}