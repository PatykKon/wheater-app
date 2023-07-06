package com.wheaterapp.domain;

import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ConditionWeather {

    private String city;
    private double averageTemperature;
    private double averageWindSpeed;
    private double pointRange;


    public ConditionWeather getTheBestWeatherByRangePoint(List<CityWeather> cityWeathers, WeatherSpecification weatherSpecification){

        List<ConditionWeather> theBestWeathers = cityWeathers
                .stream()
                .map(cityWeatherModel -> getWeatherInRange(cityWeatherModel,weatherSpecification))
                .collect(Collectors.toList());



        return theBestWeathers.stream()
                .max(Comparator.comparingDouble(ConditionWeather::getPointRange))
                .orElse(null);

    }

    private ConditionWeather getWeatherInRange(CityWeather cityWeather, WeatherSpecification weatherSpecification){

        double averageTemp = cityWeather.getTemp();
        double averageWindSpeed = cityWeather.getWind_spd();


        if(weatherSpecification.isInRange(averageTemp,averageWindSpeed)) {

            double pointRange = weatherSpecification.calculateTheBestWeatherConditions(averageWindSpeed, averageTemp);

            ConditionWeather conditionWeather = ConditionWeather.builder()
                    .pointRange(pointRange)
                    .city(cityWeather.getCity())
                    .averageTemperature(averageTemp)
                    .averageWindSpeed(averageWindSpeed)
                    .build();

            return conditionWeather;
        }
        return ConditionWeather.builder()
                .build();
    }
}