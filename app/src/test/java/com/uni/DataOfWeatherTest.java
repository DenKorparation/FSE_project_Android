package com.uni;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


class DataOfWeatherTest {
    DataOfWeather dataOfWeather;

    @Before
    void setUp() {
        dataOfWeather = new DataOfWeather();
        dataOfWeather.setTime(new Date(1635840000 * 1000L));
        dataOfWeather.setHumidity(75);
        dataOfWeather.setPressure(1010);
        dataOfWeather.setTemp((float)5.5);
        dataOfWeather.setWindSpeed((float)1.2);
        dataOfWeather.setFeelsLikeTemp((float)5.5);
    }

    @Test
    void getTemp() {
        assertEquals(5.5, dataOfWeather.getTemp(), 0.0001);
    }

    @Test
    void getFeelsLikeTemp() {
        assertEquals(5.5, dataOfWeather.getFeelsLikeTemp(), 0.0001);
    }

    @Test
    void getWindSpeed() {
        assertEquals(1.2, dataOfWeather.getWindSpeed(), 0.0001);
    }

    @Test
    void getPressure() {
        assertEquals(1010, dataOfWeather.getPressure());
    }

    @Test
    void getHumidity() {
        assertEquals(75, dataOfWeather.getHumidity());
    }

    @Test
    void getTime() {
        Date expexted = new Date(1635840000 * 1000L);
        assertEquals(expexted, dataOfWeather.getTime());
    }
}