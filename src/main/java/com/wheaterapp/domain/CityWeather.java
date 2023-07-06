package com.wheaterapp.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CityWeather{

    private String city;
    private double temp ;
    private double wind_spd;
    private String datetime;

    public CityWeather addWeatherParamsToCity(String city, List<CityWeather> weatherList) {

        CityWeather cityWeather = CityWeather.builder()
                .wind_spd(weatherList.stream()
                        .mapToDouble(CityWeather::getWind_spd)
                        .average()
                        .orElse(0.0))
                .temp(weatherList.stream()
                        .mapToDouble(CityWeather::getTemp)
                        .average().orElse(0.0))
                .datetime(weatherList.stream().findFirst().map(CityWeather::getDatetime).orElse(""))
                .city(city)
                .build();
        return cityWeather;
    }
}