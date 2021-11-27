package com.uni;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
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
/**
 * @author Vlad
 * @version 1.0
 * First page
 */
public class MainActivity extends AppCompatActivity {
    /**
     * database
     */
    public static Database database;
    /**
     * Field for town-name on the 1 page
     */
    public static EditText user_field;
    /**
     * Button for getting info on the 1 page
     */
    private Button main_btn;

    /**
     * @param savedInstanceState 1 page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Information on first page
         */
        setContentView(R.layout.activity_main);
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO
         );
        database = new Database();
        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_button);
        /**
         * Opening second page by clicking on ENTER
         */
        user_field.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
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
                    return true;
                }
                return false;
            }
        });
        /**
         * Button-click
         */
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.user_field.getText().toString().trim().equals("")) {
                }
                else {
                    database.setNameOfCity(MainActivity.user_field.getText().toString().trim());
                    /**
                     * Opening second page after button-click
                     */
                    openScrollingActivity();
                    //icon.setImageResource(map.get(database.getCurWeatherData().getIdIcon()));
                }
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }
    /**
     * Opening second page
     */
    public void openScrollingActivity(){
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }
    }

