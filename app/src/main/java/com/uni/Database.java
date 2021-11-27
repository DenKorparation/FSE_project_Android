package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Denis
 * @version 1.0
 * Database
 */
public class Database {
    /**
     * How much hours for hourly weather we have
     */
    private static final int numberOfHours = 48;
    /**
     * Parametr for map zoom
     */
    public static int zoom;
    /**
     * How much days for daily weather we have
     */
    private static final int numberOfDays = 7;
    /**
     * API Weather Key
     */
    private  static  final String API_KEYS = "c76548e17d6b42b99e631401cd0e0f75";
    /**
     * API Map key
     */
    private static final String MAP_API_KEYS = "LOSBNUlvpwa89u2MXMh5EusanAKtrRXh";
    /**
     * current data
     */
    private DataOfWeather curWeatherData = new DataOfWeather();
    /**
     * Hourly data
     */
    private DataOfWeather[] hourlyForecast = new DataOfWeather [numberOfHours];
    /**
     * Daily data
     */
    private DataOfWeather[] dailyForecast = new DataOfWeather[numberOfDays];
    /**
     * Name of city
     */
    private String nameOfCity;
    /**
     * Code of country
     */
    private String codeOfCountry;
    /**
     * Current weather condition
     */
    private String cur_Condition;
    /**
     * Part of day
     */
    private String partOfDay;
    /**
     * Data-check data
     */
    private boolean isCorrectData;
    /**
     * Geographical map
     */
    private String map;
    /**
     * Weather map
     */
    private String weatherMap;
    /**
     * Longitude coordinate of city
     */
    private double cityLongitude; //долгота
    /**
     * Latitude coordinate of city
     */
    private double cityLatitude; //широта
    /**
     * Name of the weather-map layer
     */
    public String mapLayer;


    /**
     * database
     */
    public Database(){
        zoom = 9;
        mapLayer = "temp_new";
        isCorrectData = false;
        partOfDay = "day";
        nameOfCity = "";
        cur_Condition = "Clear";
        for(int i = 0; i < numberOfHours; i++){
            hourlyForecast[i] = new DataOfWeather();
        }
        for(int i = 0; i < numberOfDays; i++){
            dailyForecast[i] = new DataOfWeather();
        }
    }

    /**
     * request
     */
    public void request() {

        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&lang=ru&units=metric&appid=" + API_KEYS);
        if (isCorrectData) {
            reqMap();
            reqHourlyForecast("https://pro.openweathermap.org/data/2.5/forecast/hourly?q=" + nameOfCity + "&cnt=48&lang=ru&units=metric&appid=" + API_KEYS);
            reqDailyForecast("https://api.openweathermap.org/data/2.5/forecast/daily?q=" + nameOfCity + "&cnt=7&lang=ru&units=metric&appid=" + API_KEYS);
        }
    }

    /**
     * Map Request
     */
    public void reqMap() {
        int xCoord, yCoord;
        xCoord = (int) ((cityLongitude + 180.d) / 360.d * Math.pow(2, zoom));
        yCoord = (int) (-(cityLatitude - 90.d) / 180.d * Math.pow(2, zoom));
        weatherMap = "https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEYS;
        map = "https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEYS + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom;
        System.out.println("https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEYS);

        System.out.println("https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEYS + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom);
    }

    /**
     * @param url current weather request
     */
    private void reqCurWeather(String url){
        System.out.println(url);
        try {
            isCorrectData = true;
            String output = getUrlContent(url);
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                if (obj.getInt("cod") != 404) {
                    curWeatherData.setTemp((float)obj.getJSONObject("main").getDouble("temp"));
                    curWeatherData.setFeelsLikeTemp((float)obj.getJSONObject("main").getDouble("feels_like"));
                    curWeatherData.setWindSpeed((float)obj.getJSONObject("wind").getDouble("speed"));
                    curWeatherData.setPressure(obj.getJSONObject("main").getInt("pressure"));
                    curWeatherData.setHumidity(obj.getJSONObject("main").getInt("humidity"));
                    curWeatherData.setTime(new Date(obj.getInt("dt") * 1000L));
                    curWeatherData.setCondition(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
                    curWeatherData.setDescription(obj.getJSONArray("weather").getJSONObject(0).getString("description"));
                    curWeatherData.setIdIcon(obj.getJSONArray("weather").getJSONObject(0).getString("icon"));
                    
                    codeOfCountry = obj.getJSONObject("sys").getString("country");

                    if(curWeatherData.getIdIcon().charAt(curWeatherData.getIdIcon().length() - 1) == 'd')
                        partOfDay = "day";
                    else
                        partOfDay = "night";

                    if(String.valueOf(obj.getJSONArray("weather").getJSONObject(0).getInt("id")).charAt(0) == '7')
                        cur_Condition = "Fog";
                    else
                        cur_Condition = curWeatherData.getCondition();

                    nameOfCity = obj.getString("name");
                    cityLatitude = obj.getJSONObject("coord").getDouble("lat");
                    cityLongitude = obj.getJSONObject("coord").getDouble("lon");
                }
                else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
        }
    }

    /**
     * @param url Hourly request
     */
    private void reqHourlyForecast(String url){
        System.out.println(url);
        try{
            String output = getUrlContent(url);
            if(!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                if(obj.getInt("cod") != 404) {
                    JSONArray list = obj.getJSONArray("list");
                    for (int i = 0; i < numberOfHours; i++) {
                        hourlyForecast[i].setTemp((float)list.getJSONObject(i).getJSONObject("main").getDouble("temp"));
                        hourlyForecast[i].setFeelsLikeTemp((float)list.getJSONObject(i).getJSONObject("main").getDouble("feels_like"));
                        hourlyForecast[i].setWindSpeed((float)list.getJSONObject(i).getJSONObject("wind").getDouble("speed"));
                        hourlyForecast[i].setPressure(list.getJSONObject(i).getJSONObject("main").getInt("pressure"));
                        hourlyForecast[i].setHumidity(list.getJSONObject(i).getJSONObject("main").getInt("humidity"));

                        //set time as Date
                        hourlyForecast[i].setTime(new Date((long)list.getJSONObject(i).getInt("dt") * 1000L));
                        hourlyForecast[i].setCondition(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                        hourlyForecast[i].setDescription(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        hourlyForecast[i].setIdIcon(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                }else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
        }
    }

    /**
     * @param url Daily request
     */
    private void reqDailyForecast(String url){
        System.out.println(url);
        try{
            String output = getUrlContent(url);
            if(!output.isEmpty()){
                JSONObject obj = new JSONObject(output);
                if(obj.getInt("cod") != 404) {
                    JSONArray list = obj.getJSONArray("list");
                    for (int i = 0; i < numberOfDays; i++) {
                        dailyForecast[i].setTempDay((float)list.getJSONObject(i).getJSONObject("temp").getDouble("day"));
                        dailyForecast[i].setTempNight((float)list.getJSONObject(i).getJSONObject("temp").getDouble("night"));
                        dailyForecast[i].setTemp((float)list.getJSONObject(i).getJSONObject("temp").getDouble("eve"));
                        dailyForecast[i].setFeelsLikeTempNight((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("night"));
                        dailyForecast[i].setFeelsLikeTempDay((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("day"));
                        dailyForecast[i].setFeelsLikeTemp((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("eve"));
                        dailyForecast[i].setWindSpeed((float)list.getJSONObject(i).getDouble("speed"));
                        dailyForecast[i].setPressure(list.getJSONObject(i).getInt("pressure"));
                        dailyForecast[i].setHumidity(list.getJSONObject(i).getInt("humidity"));
                        //set time as Date
                        dailyForecast[i].setTime(new Date((long)list.getJSONObject(i).getInt("dt") * 1000L));
                        dailyForecast[i].setCondition(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                        dailyForecast[i].setDescription(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        dailyForecast[i].setIdIcon(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                }else
                    isCorrectData = false;
            }
            else
                isCorrectData = false;
        } catch (Exception e){
            isCorrectData = false;
        }
    }


    /**
     * @param urlAdress url
     * @return
     * @throws IOException
     */
    private static String getUrlContent(String urlAdress) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        URL url = new URL(urlAdress);
        try{
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while((line = reader.readLine()) != null)
                buffer.append(line).append("\n");

            return buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
            return "{\"cod\":\"404\",\"message\":\"city not found\"}";
        }
        finally {
            if(connection != null)
                connection.disconnect();

            if(reader != null)
                reader.close();
        }
    }

    /**
     * @return current weather data
     */
    public DataOfWeather getCurWeatherData() {
        return curWeatherData;
    }

    /**
     * @return Hourly forecast
     */
    public DataOfWeather[] getHourlyForecast() {
        return hourlyForecast;
    }

    /**
     * @return Daily forecast
     */
    public DataOfWeather[] getDailyForecast() {
        return dailyForecast;
    }

    /**
     * @param nameOfCity name of city
     */
    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    /**
     * @return Getting name of city
     */
    public String getNameOfCity(){
        return nameOfCity;
    }

    /**
     * @return correct data check
     */
    public boolean isCorrectData() {
        return isCorrectData;
    }

    /**
     * @return Part of day
     */
    public String getPartOfDay() {
        return partOfDay;
    }

    /**
     * @return Current condition
     */
    public String getCur_Condition() {
        return cur_Condition;
    }

    /**
     * @return getting map
     */
    public String getMap() {
        return map;
    }

    /**
     * @return getting citylontitude
     */
    public double getCityLongitude() {
        return cityLongitude;
    }

    /**
     * @return getting citylatitude
     */
    public double getCityLatitude() {
        return cityLatitude;
    }

    /**
     * @return weather map
     */
    public String getWeatherMap() {
        return weatherMap;
    }

    /**
     * @param mapLayer setting weather map layer
     */
    public void setMapLayer(String mapLayer) {
        this.mapLayer = mapLayer;
    }

    /**
     * max zoom
     */
    public void zoomIncrement(){
        if(zoom < 20)
            zoom++;
    };

    /**
     * min zoom
     */
    public void zoomDecrement(){
        if(zoom > 1)
            zoom--;
    };

    /**
     * @return Country code
     */
    public String getCodeOfCountry() {
        return codeOfCountry;
    }

}
