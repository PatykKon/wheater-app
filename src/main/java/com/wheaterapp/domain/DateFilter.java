package com.wheaterapp.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateFilter {

    private boolean isWithinDateRange(RequestDateWeather requestDateWeather, String startDate) {

        LocalDate currentDate = LocalDate.parse(startDate.formatted(DateTimeFormatter.ISO_DATE));
        LocalDate givenData = LocalDate.parse(requestDateWeather.startDay());

        return !currentDate.isBefore(givenData);
    }

    public List<CityWeather> filteringWeatherByRequestDate(WeatherDataDto weatherDataDto, RequestDateWeather requestDateWeather) {

        List<CityWeather> weatherInRequestDate = new ArrayList<>();



        for (WeatherDto weather : weatherDataDto.data()) {
            if (isWithinDateRange(requestDateWeather,weather.datetime())) {
                CityWeather weatherData1 = CityWeather.builder()
                        .city(weather.city())
                        .temp(weather.temp())
                        .wind_spd(weather.wind_spd())
                        .datetime(weather.datetime())
                        .build();

                weatherInRequestDate.add(weatherData1);
            }
        }
        return weatherInRequestDate;
    }
}
