package com.uni;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {
    Database database = new Database();

    @Before
    public void setUp() {
        database.setNameOfCity("Minsk");
        database.request();
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

    @Test(timeout = 10000)
    public void request() throws Exception{
            database.request();
    }

    @Test(timeout = 10000)
    public void requestIncorrectData() throws Exception{
        database.setNameOfCity("1");
        database.request();
        assertEquals(false, database.isCorrectData());
    }


    @Test
    public void setNameOfCity() {
        database.setNameOfCity("Minsk");
        assertEquals("Minsk", database.getNameOfCity());
    }

    @Test
    public void getNameOfCity() {
        assertEquals("Минск", database.getNameOfCity());
    }

    @Test
    public void isCorrectData() {
        assertEquals(true, database.isCorrectData());
    }

    @Test
    public void getPartOfDay() {
        assertNotNull(database.getPartOfDay());
    }

    @Test
    public void getCur_Condition() {
        assertNotNull(database.getCur_Condition());
    }

    @Test
    public void getMap() {
        assertNotNull(database.getMap());
    }

    @Test
    public void getWeatherMap() {
        assertNotNull(database.getWeatherMap());
    }

    @Test
    public void setMapLayer() throws Exception{
        database.setMapLayer("temp_new");
        database.reqMap();
        assertNotNull(database.getMap());
    }

    @Test
    public void zoomIncrement() throws Exception{
        for(int i = 0; i < 13; i++){
            database.zoomIncrement();
        }
    }

    @Test
    public void zoomDecrement() throws Exception{
        for(int i = 0; i < 10; i++){
            database.zoomDecrement();
        }
    }

    @Test
    public void getCodeOfCountry() {
        assertEquals("BY", database.getCodeOfCountry());
    }
}