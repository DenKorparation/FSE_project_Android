package com.uni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private Database database;
    private EditText user_field;
    private Button main_btn;
    private TextView result_info;
    private TextView feelslike;
    private TextView windspeed;
    private TextView pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database();

        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_button);
        result_info = findViewById(R.id.resultinfo);
        feelslike = findViewById(R.id.feelslike);
        windspeed = findViewById(R.id.windspeed);
        pressure = findViewById(R.id.pressure);


        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                else{
                    database.setNameOfCity(user_field.getText().toString().trim());
                    new Request().execute();
                    System.out.println("|" + user_field.getText().toString().trim() + "|  " + database.getCurWeatherData().getTemp());

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
            {result_info.setText(database.getNameOfCity() + "\n" + "Температура: " + Float.toString(database.getCurWeatherData().getTemp())+"°C");
                feelslike.setText( "Температура ощущается как: "+Float.toString(database.getCurWeatherData().getFeelsLikeTemp())+"°C");
                windspeed.setText("Скорость ветра: "+Float.toString(database.getCurWeatherData().getWindSpeed())+" km/h");
                pressure.setText("Давление: "+Float.toString(database.getCurWeatherData().getPressure())+" Pa");
                }

            else
                result_info.setText("Incorrect data");
                        ;

        }

        }

}