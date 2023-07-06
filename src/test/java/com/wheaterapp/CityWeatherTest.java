package com.wheaterapp;


import com.wheaterapp.domain.CityWeather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CityWeatherTest {

    @Test
    void shouldReturnCityWeatherWithParametersFromList_whenAllParamsOk() {

        String city = "city";
        List<CityWeather> weatherList = new ArrayList<>();
        weatherList.add(new CityWeather(city,10,10,"2023-07-01"));
        weatherList.add(new CityWeather(city,10,10,"2023-07-01"));


        CityWeather expects = CityWeather.builder()
                .city(city)
                .temp(10)
                .wind_spd(10)
                .datetime("2023-07-01")
                .build();

        CityWeather result = new CityWeather().addWeatherParamsToCity(city,weatherList);

        Assertions.assertEquals(expects,result);
    }
    @Test
    void shouldReturnZeroValues_whenListParamsIsEmpty() {

        String city ="city";
        List<CityWeather > weatherList = new ArrayList<>();

        CityWeather expects = CityWeather.builder()
                .datetime("")
                .wind_spd(0)
                .temp(0)
                .city("city")
                .build();
        CityWeather result = new CityWeather().addWeatherParamsToCity(city,weatherList);

        Assertions.assertEquals(expects,result);
    }

    @Test
    void shouldReturnEmptyCityString_whenCityDoesNotFind() {

        String city ="";
        List<CityWeather > weatherList = new ArrayList<>();
        weatherList.add(new CityWeather(city,10,10,"2023-07-01"));
        weatherList.add(new CityWeather(city,10,10,"2023-07-01"));

        CityWeather expects = CityWeather.builder()
                .datetime("2023-07-01")
                .wind_spd(10)
                .temp(10)
                .city("")
                .build();
        CityWeather result = new CityWeather().addWeatherParamsToCity(city,weatherList);

        Assertions.assertEquals(expects,result);
    }
}
