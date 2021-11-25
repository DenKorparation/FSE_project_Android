package com.uni;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

class DatabaseTest {
    Database database;

    @Before
    void setUp() {
        database = new Database();
        database.setNameOfCity("Minsk");
    }

    @Test
    void getCurWeatherData() {
        assertNotNull(database.getCurWeatherData());
    }

    @Test
    void getHourlyForecast() {
        assertNotNull(database.getHourlyForecast());
    }

    @Test
    void getDailyForecast() {
        assertNotNull(database.getDailyForecast());
    }

    /*@Test
    void request() throws Exception{
        assertTimeout(ofSeconds(10000), () -> {
            database.request();
        });
    }*/
}