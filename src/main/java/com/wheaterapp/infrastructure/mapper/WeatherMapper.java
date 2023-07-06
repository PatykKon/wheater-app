package com.wheaterapp.infrastructure.mapper;

import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.infrastructure.dto.CityResponse;

public class WeatherMapper {

    public CityResponse mapToCityResponse(ConditionWeather conditionWeather) {
        String city = conditionWeather.getCity();
        Double temp = conditionWeather.getAverageTemperature();
        Double windSpeed = conditionWeather.getAverageWindSpeed();


        return new CityResponse(city,temp,windSpeed);
    }
}
