package com.wheaterapp.aplication;

import com.wheaterapp.infrastructure.clientapi.dto.WeatherDataDto;
import com.wheaterapp.infrastructure.clientapi.dto.WeatherDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDayDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDto;
import com.wheaterapp.infrastructure.dto.RequestDateWeather;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateFilter {
    private boolean isWithinDateRange(RequestDateWeather requestDateWeather, String startDate) {

        LocalDate currentDate = LocalDate.parse(startDate.formatted(DateTimeFormatter.ISO_DATE));
        LocalDate givenData = LocalDate.parse(requestDateWeather.startDay());

        return !currentDate.isBefore(givenData);
    }


    public CityWeatherDto filteringWeatherByRequestDate(WeatherDataDto weatherDataDto, RequestDateWeather requestDateWeather) {

        List<CityWeatherDayDto> allDayForCity = new ArrayList<>();

        for (WeatherDto weather : weatherDataDto.data()) {
            if (isWithinDateRange(requestDateWeather,weather.datetime())) {
                CityWeatherDayDto cityWeatherDay = CityWeatherDayDto.builder()
                        .wind_spd(weather.wind_spd())
                        .temp(weather.temp())
                        .city(weatherDataDto.city_name())
                        .datetime(weather.datetime())
                        .build();
                allDayForCity.add(cityWeatherDay);
            }
        }
        CityWeatherDto cityWeather = CityWeatherDto.builder()
                .cityWeatherDay(allDayForCity)
                .city(weatherDataDto.city_name())
                .build();
        return cityWeather;
    }
}
