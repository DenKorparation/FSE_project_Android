package com.uni;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
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

import static com.uni.ScrollingActivity.*;

public class MainActivity extends AppCompatActivity {

    public static Database database;
    public static EditText user_field;
    private Button main_btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database();
        textView = findViewById(R.id.textView);
        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_button);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (MainActivity.user_field.getText().toString().trim().equals("")) {

                }
                else {
                    database.setNameOfCity(MainActivity.user_field.getText().toString().trim());

                    openScrollingActivity();

                    //icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                }


                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    public void openScrollingActivity(){

        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }


    }

