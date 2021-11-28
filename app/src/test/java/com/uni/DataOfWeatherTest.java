package com.uni;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class DataOfWeatherTest {
    DataOfWeather dataOfWeather;

    public DataOfWeatherTest () {

    }

    @Before
    public void setUp() {
        dataOfWeather = new DataOfWeather();
        dataOfWeather.setTime(new Date(1635840000 * 1000L));
        dataOfWeather.setHumidity(75);
        dataOfWeather.setPressure(1010);
        dataOfWeather.setTemp((float)5.5);
        dataOfWeather.setWindSpeed((float)1.2);
        dataOfWeather.setFeelsLikeTemp((float)5.5);
        dataOfWeather.setFeelsLikeTempDay((float)5.5);
        dataOfWeather.setFeelsLikeTempNight((float)5.5);
        dataOfWeather.setTempNight((float) 5.5);
        dataOfWeather.setTempDay((float) 5.5);
    }

    @Test
    public void getTemp() {
        assertEquals(5.5, dataOfWeather.getTemp(), 0.0001);
    }

    @Test
    public void getFeelsLikeTemp() {
        assertEquals(5.5, dataOfWeather.getFeelsLikeTemp(), 0.0001);
    }

    @Test
    public void getWindSpeed() {
        assertEquals(1.2, dataOfWeather.getWindSpeed(), 0.0001);
    }

    @Test
    public void getPressure() {
        assertEquals(1010, dataOfWeather.getPressure(), 0.0001);
    }

    @Test
    public void getHumidity() {
        assertEquals(75, dataOfWeather.getHumidity(), 0.0001);
    }

    @Test
    public void getTime() {
        Date expexted = new Date(1635840000 * 1000L);
        assertEquals(expexted, dataOfWeather.getTime());
    }

    @Test
    public void setDescription() {
        dataOfWeather.setDescription("ясно");
        assertEquals("ясно", dataOfWeather.getDescription());
    }

    @Test
    public void getFeelsLikeTempDay() {
        assertEquals(5.5, dataOfWeather.getFeelsLikeTempDay(), 0.0001);
    }

    @Test
    public void setFeelsLikeTempDay() {
        dataOfWeather.setFeelsLikeTempDay((float) 10.0);
        assertEquals(10.0, dataOfWeather.getFeelsLikeTempDay(), 0.0001);
    }

    @Test
    public void getFeelsLikeTempNight() {
        assertEquals(5.5, dataOfWeather.getFeelsLikeTempNight(), 0.0001);
    }

    @Test
    public void setFeelsLikeTempNight() {
        dataOfWeather.setFeelsLikeTempNight((float) 10.0);
        assertEquals(10.0, dataOfWeather.getFeelsLikeTempNight(), 0.0001);
    }

    @Test
    public void getTempDay() {
        assertEquals(5.5, dataOfWeather.getTempDay(), 0.0001);
    }

    @Test
    public void getTempNight() {
        assertEquals(5.5, dataOfWeather.getTempNight(), 0.0001);
    }
}