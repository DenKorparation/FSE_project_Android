package com.uni;

import java.util.Date;

public class DataOfWeather {
    private float temp;
    private String description;
    private float feelsLikeTemp;
    private float windSpeed;
    private int pressure;
    private int humidity;
    private Date time;
    private String idIcon;
    private String condition;
    private float tempDay;
    private float tempNight;
    private float feelsLikeTempDay;
    private  float feelsLikeTempNight;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(float feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTempDay() {
        return tempDay;
    }

    public void setTempDay(float tempDay) {
        this.tempDay = tempDay;
    }

    public float getTempNight() {
        return tempNight;
    }

    public void setTempNight(float tempNight) {
        this.tempNight = tempNight;
    }

    public float getFeelsLikeTempDay() {
        return feelsLikeTempDay;
    }

    public void setFeelsLikeTempDay(float feelsLikeTempDay) {
        this.feelsLikeTempDay = feelsLikeTempDay;
    }

    public float getFeelsLikeTempNight() {
        return feelsLikeTempNight;
    }

    public void setFeelsLikeTempNight(float feelsLikeTempNight) {
        this.feelsLikeTempNight = feelsLikeTempNight;
    }

}
