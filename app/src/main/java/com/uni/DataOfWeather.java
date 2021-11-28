package com.uni;

import java.util.Date;

/**
 * this class contains information about weather at a particular moment
 * @author Den
 * @version 1.0
 */
public class DataOfWeather {
    /** temp store information about temperature in degrees Celsius  */
    private float temp;
    /** description store full description of weather condition at selected moment in the form of a few words  */
    private String description;
    /** feelsLikeTemp store information about how the temperature feels in degrees Celsius */
    private float feelsLikeTemp;
    /** windSpeed store information about wind speed in m/s */
    private float windSpeed;
    /** pressure store information about  pressure in hPa */
    private int pressure;
    /** humidity store information about humidity in % */
    private int humidity;
    /** time store information about time at the selected moment */
    private Date time;
    /** idIcon store information about id of condition icon */
    private String idIcon;
    /** condition store short description of weather condition at selected moment in the form of a single word */
    private String condition;
    /** tempDay store information about daytime temperature for the selected day for daily forecast in degrees Celsius */
    private float tempDay;
    /** tempNight store information about temperature of night for the selected day for daily forecast in degrees Celsius */
    private float tempNight;
    /** feelsLikeTempDay store information about how the daytime temperature feels for the selected day for daily forecast in degrees Celsius  */
    private float feelsLikeTempDay;
    /** feelsLikeTempNight store information about how the temperature at night feels for the selected day for daily forecast in degrees Celsius  */
    private  float feelsLikeTempNight;


    /**
     * Function for getting the field value {@link DataOfWeather#temp}
     * @return returns value of temperature
     */
    public float getTemp() {
        return temp;
    }

    /**
     * setter for temp field in {@link DataOfWeather}
     * @param temp to set in {@link DataOfWeather#temp}
     */
    public void setTemp(float temp) {
        this.temp = temp;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#feelsLikeTemp}
     * @return returns value of how the temperature feels
     */
    public float getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    /**
     * setter for feelsLikeTemp field in {@link DataOfWeather}
     * @param feelsLikeTemp to set in {@link DataOfWeather#feelsLikeTemp}
     */
    public void setFeelsLikeTemp(float feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#windSpeed}
     * @return returns value of wind speed
     */
    public float getWindSpeed() {
        return windSpeed;
    }

    /**
     * setter for windSpeed field in {@link DataOfWeather}
     * @param windSpeed to set in {@link DataOfWeather#windSpeed}
     */
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#pressure }
     * @return returns value of pressure
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * setter for pressure field in {@link DataOfWeather}
     * @param pressure to set in {@link DataOfWeather#pressure}
     */
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#humidity}
     * @return returns value of humidity
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * setter for humidity field in {@link DataOfWeather}
     * @param humidity to set in {@link DataOfWeather#humidity}
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**Function for getting the field value {@link DataOfWeather#time}
     * @return returns value of time at the selected moment
     */
    public Date getTime() {
        return time;
    }

    /**
     * setter for time field in {@link DataOfWeather}
     * @param time to set in {@link DataOfWeather#time}
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#condition}
     * @return returns Condition as String
     */
    public String getCondition() {
        return condition;
    }

    /**
     * setter for condition field in {@link DataOfWeather}
     * @param condition to set in {@link DataOfWeather#condition}
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#idIcon}
     * @return returns id of Icon as String
     */
    public String getIdIcon() {
        return idIcon;
    }

    /**
     * setter for idIcon field in {@link DataOfWeather}
     * @param idIcon to set in {@link DataOfWeather#idIcon}
     */
    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#description}
     * @return returns description as String
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description field in {@link DataOfWeather}
     * @param description to set in {@link DataOfWeather#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**Function for getting the field value {@link DataOfWeather#tempDay}.<br>
     * <strong>Use only for daily forecast!</strong>
     * @return returns value of daytime temperature
     */
    public float getTempDay() {
        return tempDay;
    }

    /**
     * setter for tempDay field in {@link DataOfWeather}<br>
     * <strong>Use only for daily forecast!</strong>
     * @param tempDay to set in {@link DataOfWeather#tempDay}
     */
    public void setTempDay(float tempDay) {
        this.tempDay = tempDay;
    }

    /**Function for getting the field value {@link DataOfWeather#tempNight}.<br>
     * <strong>Use only for daily forecast!</strong>
     * @return returns value of temperature at night
     */
    public float getTempNight() {
        return tempNight;
    }

    /**
     * setter for tempNight field in {@link DataOfWeather}<br>
     * <strong>Use only for daily forecast!</strong>
     * @param tempNight to set in {@link DataOfWeather#tempNight}
     */
    public void setTempNight(float tempNight) {
        this.tempNight = tempNight;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#feelsLikeTempDay}.<br>
     * <strong>Use only for daily forecast!</strong>
     * @return returns value of how daytime temperature feels
     */
    public float getFeelsLikeTempDay() {
        return feelsLikeTempDay;
    }

    /**setter for feelsLikeTempDay field in {@link DataOfWeather}<br>
     * <strong>Use only for daily forecast!</strong>
     * @param feelsLikeTempDay to set in {@link DataOfWeather#feelsLikeTempDay}
     */
    public void setFeelsLikeTempDay(float feelsLikeTempDay) {
        this.feelsLikeTempDay = feelsLikeTempDay;
    }

    /**
     * Function for getting the field value {@link DataOfWeather#feelsLikeTempNight}.<br>
     * <strong>Use only for daily forecast!</strong>
     * @return returns value of how temperature at night feels
     */
    public float getFeelsLikeTempNight() {
        return feelsLikeTempNight;
    }

    /**setter for feelsLikeTempNight field in {@link DataOfWeather}<br>
     * <strong>Use only for daily forecast!</strong>
     * @param feelsLikeTempNight to set in {@link DataOfWeather#feelsLikeTempNight}
     */
    public void setFeelsLikeTempNight(float feelsLikeTempNight) {
        this.feelsLikeTempNight = feelsLikeTempNight;
    }

}
