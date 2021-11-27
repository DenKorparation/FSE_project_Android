package com.uni;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DatabaseTest {
    Database database;

    public DatabaseTest()
    {

    }

    @Before
    public void setUp() {
        database = new Database();
        database.setNameOfCity("Minsk");
    }

    @Test
    public void getCurWeatherData() {
        assertNotNull(database.getCurWeatherData());
    }

    @Test
    public void getHourlyForecast() {
        assertNotNull(database.getHourlyForecast());
    }

    @Test
    public void getDailyForecast() {
        assertNotNull(database.getDailyForecast());
    }

    /*@Test
    void request() throws Exception{
        assertTimeout(ofSeconds(10000), () -> {
            database.request();
        });
    }*/
}