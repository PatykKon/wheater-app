package com.wheaterapp.domain;

import com.wheaterapp.infrastructure.dto.CityWeatherDayDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDto;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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


    public ConditionWeather getTheBestWeatherByRangePoint(List<CityWeatherDto> weathers, WeatherSpecification weatherSpecification){


        List<ConditionWeather> theBestWeathers = new ArrayList<>();
      for(CityWeatherDto cityWeatherDto : weathers){
          ConditionWeather conditionWeather = getWeatherInRange(cityWeatherDto,weatherSpecification);
          theBestWeathers.add(conditionWeather);
      }



        return theBestWeathers.stream()
                .max(Comparator.comparingDouble(ConditionWeather::getPointRange))
                .orElse(null);

    }

    private ConditionWeather getWeatherInRange(
            CityWeatherDto cityWeatherDayDto,WeatherSpecification weatherSpecification
    ) {


        double averageTemp = cityWeatherDayDto.cityWeatherDay()
                .stream().mapToDouble(CityWeatherDayDto::temp).average().orElse(0.0);
        double averageWindSpeed = cityWeatherDayDto.cityWeatherDay()
                .stream().mapToDouble(CityWeatherDayDto::wind_spd).average().orElse(0.0);


        if (weatherSpecification.isInRange(averageTemp, averageWindSpeed)) {

            double pointRange = weatherSpecification.calculateTheBestWeatherConditions(averageWindSpeed, averageTemp);

            ConditionWeather conditionWeather = ConditionWeather.builder()
                    .pointRange(pointRange)
                    .averageTemperature(averageTemp)
                    .city(cityWeatherDayDto.city())
                    .averageWindSpeed(averageWindSpeed)
                    .build();

            return conditionWeather;
        }
        return ConditionWeather.builder()
                .build();
    }

}
