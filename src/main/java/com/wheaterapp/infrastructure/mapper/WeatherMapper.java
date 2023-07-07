package com.wheaterapp.infrastructure.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.infrastructure.dto.CityResponse;
import com.wheaterapp.infrastructure.dto.CityWeatherDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDayDto;

public class WeatherMapper {

    private final ObjectMapper objectMapper;

    public WeatherMapper() {
        this.objectMapper = new ObjectMapper();
    }


    public CityResponse mapToCityResponse(ConditionWeather conditionWeather) {
        String city = conditionWeather.getCity();
        Double temp = conditionWeather.getAverageTemperature();
        Double windSpeed = conditionWeather.getAverageWindSpeed();


        return new CityResponse(city, temp, windSpeed);
    }
}


