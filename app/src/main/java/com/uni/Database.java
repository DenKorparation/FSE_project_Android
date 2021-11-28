package com.uni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * this class contains information about current weather and hourly(48 hours) and daily(7 days) forecast and some geo info about city
 * @author Den
 * @version 1.0
 */
public class Database {
    /**
     * numberOfHours store number of hours for hourly forecast
     */
    private static final int numberOfHours = 48;
    /**
     * zoom store zoom of map
     */
    private int zoom;
    /**
     * numberOfDays store number of days for daily forecast
     */
    private static final int numberOfDays = 7;
    /**
     * API_KEY store API key of OpenWeatherMap.org
     */
    private  static  final String API_KEY = "c76548e17d6b42b99e631401cd0e0f75";
    /**
     * MAP_API_KEY store API key of www.mapquestapi.com
     */
    private static final String MAP_API_KEY = "LOSBNUlvpwa89u2MXMh5EusanAKtrRXh";
    /**
     * curWeatherData store information about current weather
     */
    private DataOfWeather curWeatherData = new DataOfWeather();
    /**
     * hourlyForecast store information about hourly forecast(48 hours)
     */
    private DataOfWeather[] hourlyForecast = new DataOfWeather [numberOfHours];
    /**
     * dailyForecast store information about daily forecast(7 days)
     */
    private DataOfWeather[] dailyForecast = new DataOfWeather[numberOfDays];
    /**
     * nameOfCity store name of selected city
     */
    private String nameOfCity;
    /**
     * codeOfCountry store code of country of selected city
     */
    private String codeOfCountry;
    /**
     * cur_Condition store current weather condition
     */
    private String cur_Condition;
    /**
     * partOfDay store part of day at the moment
     */
    private String partOfDay;
    /**
     * isCorrectData indicates whether the correct data has been received
     */
    private boolean isCorrectData;
    /**
     * map store geo map of this city
     */
    private String map;
    /**
     * weatherMap store weather map of this city
     */
    private String weatherMap;
    /**
     * cityLongitude store Longitude of this city
     */
    private double cityLongitude; //долгота
    /**
     * cityLatitude store Latitude of this city
     */
    private double cityLatitude; //широта
    /**
     * mapLayer store type of layer of weather map
     */
    private String mapLayer;


    /**
     * default constructor
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
     * call all request
     */
    public void request() {

        reqCurWeather("https://api.openweathermap.org/data/2.5/weather?q=" + nameOfCity + "&lang=ru&units=metric&appid=" + API_KEY);
        if (isCorrectData) {
            reqMap();
            reqHourlyForecast("https://pro.openweathermap.org/data/2.5/forecast/hourly?q=" + nameOfCity + "&cnt=48&lang=ru&units=metric&appid=" + API_KEY);
            reqDailyForecast("https://api.openweathermap.org/data/2.5/forecast/daily?q=" + nameOfCity + "&cnt=7&lang=ru&units=metric&appid=" + API_KEY);
        }
    }

    /**
     * receiving weather map and geo map (.png)
     */
    public void reqMap() {
        int xCoord, yCoord;
        xCoord = (int) ((cityLongitude + 180.d) / 360.d * Math.pow(2, zoom));
        yCoord = (int) (-(cityLatitude - 90.d) / 180.d * Math.pow(2, zoom));
        weatherMap = "https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEY;
        map = "https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEY + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom;
        System.out.println("https://tile.openweathermap.org/map/" + mapLayer + "/" + Integer.toString(zoom) + "/" + Integer.toString(xCoord) + "/" + Integer.toString(yCoord) + ".png?appid=" + API_KEY);

        System.out.println("https://www.mapquestapi.com/staticmap/v5/map?key=" + MAP_API_KEY + "&center=" +
                (-(yCoord + 0.5) * 180.d / Math.pow(2, zoom) + 90.d) + "," + Double.toString((xCoord + 0.5) * 360.d / Math.pow(2, zoom) - 180.d) +
                "&size=256,256@2x&zoom=" + zoom);
    }

    /**
     * receiving current weather data
     * @param url url of call API of openWeatherMap for current weather data
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
     * receiving hourly forecast
     * @param url url of call API of openWeatherMap for hourly forecast
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
     * receiving daily forecast
     * @param url url of call API of openWeatherMap for daily forecast
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
                        dailyForecast[i].setFeelsLikeTempNight((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("night"));
                        dailyForecast[i].setFeelsLikeTempDay((float)list.getJSONObject(i).getJSONObject("feels_like").getDouble("day"));
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
     * getting content by url request
     * @param urlAdress url adress for which we want to get content
     * @return content as String(for this project JSON file)
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
     * Function for getting the field value {@link Database#curWeatherData}
     * @return returns information about current weather
     */
    public DataOfWeather getCurWeatherData() {
        return curWeatherData;
    }

    /**Function for getting the field value {@link Database#hourlyForecast}
     * @return returns information about hourly weather forecast(48 hours)
     */
    public DataOfWeather[] getHourlyForecast() {
        return hourlyForecast;
    }

    /**Function for getting the field value {@link Database#dailyForecast}
     * @return  returns information about daily weather forecast(7 days)
     */
    public DataOfWeather[] getDailyForecast() {
        return dailyForecast;
    }

    /**
     * setter for nameOfCity field in {@link Database}
     * @param nameOfCity to set name of selected city
     */
    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    /**
     * Function for getting the field value {@link Database#nameOfCity}
     * @return returns name of selected city
     */
    public String getNameOfCity(){
        return nameOfCity;
    }

    /**
     * returns true if the correct data is received
     * false in other cases
     * @return value of {@link Database#isCorrectData}
     */
    public boolean isCorrectData() {
        return isCorrectData;
    }

    /**Function for getting the field value {@link Database#partOfDay}
     * @return returns part of day as String
     */
    public String getPartOfDay() {
        return partOfDay;
    }

    /**
     * Function for getting the field value {@link Database#cur_Condition}
     * @return returns current weather condition as String
     */
    public String getCur_Condition() {
        return cur_Condition;
    }

    /**
     * Function for getting the field value {@link Database#map}
     * @return returns geo map as Image
     */
    public String getMap() {
        return map;
    }

    /**
     * Function for getting the field value {@link Database#weatherMap}
     * @return returns weather map as Image
     */
    public String getWeatherMap() {
        return weatherMap;
    }

    /**
     * setter for mapLayer field in {@link Database}
     * @param mapLayer to set current weather map layer
     */
    public void setMapLayer(String mapLayer) {
        this.mapLayer = mapLayer;
    }

    /**
     * this method zooms in on the map
     * but max value of {@link Database#zoom} = 20
     */
    public void zoomIncrement(){
        if(zoom < 20)
            zoom++;
    };

    /**
     * this method zooms out on the map
     * but min value of {@link Database#zoom} = 0
     */
    public void zoomDecrement(){
        if(zoom > 0)
            zoom--;
    };

    /**
     * Function for getting the field value {@link Database#codeOfCountry}
     * @return returns code of country in which the selected city is located
     */
    public String getCodeOfCountry() {
        return codeOfCountry;
    }

}
