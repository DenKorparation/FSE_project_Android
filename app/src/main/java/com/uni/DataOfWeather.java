package com.uni;

import java.util.Date;

/**
 * @author Denis
 * @version 1.0
 * Weather Data
 */
public class DataOfWeather {
    /**
     * temperature data
     */
    private float temp;
    /**
     * description
     */
    private String description;
    /**
     * feelslike temperature data
     */
    private float feelsLikeTemp;
    /**
     * windspeed data
     */
    private float windSpeed;
    /**
     * pressure data
     */
    private int pressure;
    /**
     * humidity data
     */
    private int humidity;
    /**
     * date data
     */
    private Date time;
    /**
     * icon data
     */
    private String idIcon;
    /**
     * condition data
     */
    private String condition;
    /**
     * Day temperature
     */
    private float tempDay;
    /**
     * Night temperature
     */
    private float tempNight;
    /**
     * Feelslike day temperature
     */
    private float feelsLikeTempDay;
    /**
     * feelslike night temperature
     */
    private  float feelsLikeTempNight;

    /**
     * @return getting temperature
     */
    public float getTemp() {
        return temp;
    }

    /**
     * @param temp setting temperature
     */
    public void setTemp(float temp) {
        this.temp = temp;
    }

    /**
     * @return getting feelslike temperature
     */
    public float getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    /**
     * @param feelsLikeTemp setting feelslike temperature
     */
    public void setFeelsLikeTemp(float feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    /**
     * @return getting windspeed
     */
    public float getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed setting windspeed
     */
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return getting pressure
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * @param pressure setting pressure
     */
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    /**
     * @return getting Humidity
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * @param humidity setting humidity
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * @return getting time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time setting time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return getting condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition setting condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return getting IdIcon
     */
    public String getIdIcon() {
        return idIcon;
    }

    /**
     * @param idIcon setting IdIcon
     */
    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    /**
     * @return getting Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description setting Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return getting Day temperature
     */
    public float getTempDay() {
        return tempDay;
    }

    /**
     * @param tempDay setting day temperature
     */
    public void setTempDay(float tempDay) {
        this.tempDay = tempDay;
    }

    /**
     * @return getting Night temperature
     */
    public float getTempNight() {
        return tempNight;
    }

    /**
     * @param tempNight setting night temperature
     */
    public void setTempNight(float tempNight) {
        this.tempNight = tempNight;
    }

    /**
     * @return getting feelslike Day temperature
     */
    public float getFeelsLikeTempDay() {
        return feelsLikeTempDay;
    }

    /**
     * @param feelsLikeTempDay setting feelslike Day temperature
     */
    public void setFeelsLikeTempDay(float feelsLikeTempDay) {
        this.feelsLikeTempDay = feelsLikeTempDay;
    }

    /**
     * @return getting feelslike Night temperature
     */
    public float getFeelsLikeTempNight() {
        return feelsLikeTempNight;
    }

    /**
     * @param feelsLikeTempNight setting feelslike night temperature
     */
    public void setFeelsLikeTempNight(float feelsLikeTempNight) {
        this.feelsLikeTempNight = feelsLikeTempNight;
    }

}
