package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class Database {
    private static final int numberOfHours = 48;
    private static final int numberOfDays = 7;
    private DataOfWeather curWeatherData = new DataOfWeather();
    private DataOfWeather[] hourlyForecast = new DataOfWeather [numberOfHours];
    private DataOfWeather[] dailyForecast = new DataOfWeather[numberOfDays];
    private String nameOfCity;
    private String cur_Condition;
    private String partOfDay;
    private boolean isCorrectData;

    public Database(){
        isCorrectData = false;
        partOfDay = "day";
        nameOfCity = "";
        cur_Condition = "clear";
        for(int i = 0; i < numberOfHours; i++){
            hourlyForecast[i] = new DataOfWeather();
        }
        for(int i = 0; i < numberOfDays; i++){
            dailyForecast[i] = new DataOfWeather();
        }
    }

    public void request()
    {
        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&units=metric&appid=c76548e17d6b42b99e631401cd0e0f75");
        reqHourlyForecast("https://pro.openweathermap.org/data/2.5/forecast/hourly?q=" + nameOfCity + "&cnt=48&units=metric&appid=c76548e17d6b42b99e631401cd0e0f75");
    }
    private void reqCurWeather(String url){
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
                    curWeatherData.setTime(new Date(obj.getInt("dt")));
                    curWeatherData.setCondition(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
                    curWeatherData.setIdIcon(obj.getJSONArray("weather").getJSONObject(0).getString("icon"));

                    if(curWeatherData.getIdIcon().charAt(curWeatherData.getIdIcon().length() - 1) == 'd')
                        partOfDay = "day";
                    else
                        partOfDay = "night";

                    if(String.valueOf(obj.getJSONArray("weather").getJSONObject(0).getInt("id")).charAt(0) == '7')
                        cur_Condition = "fog";
                    else
                        cur_Condition = curWeatherData.getCondition();
                }
                else
                    isCorrectData = false;
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    private void reqHourlyForecast(String url){
        try{
            isCorrectData = true;
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
                        hourlyForecast[i].setTime(new Date(list.getJSONObject(i).getInt("dt")));
                        hourlyForecast[i].setCondition(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                        hourlyForecast[i].setIdIcon(list.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                }else
                    isCorrectData = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

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

    public DataOfWeather getCurWeatherData() {
        return curWeatherData;
    }

    public DataOfWeather[] getHourlyForecast() {
        return hourlyForecast;
    }

    public DataOfWeather[] getDailyForecast() {
        return dailyForecast;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public String getNameOfCity(){
        return nameOfCity;
    }

    public boolean isCorrectData() {
        return isCorrectData;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public String getCur_Condition() {
        return cur_Condition;
    }
}
