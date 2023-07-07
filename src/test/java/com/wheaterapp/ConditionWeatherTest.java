package com.wheaterapp;


import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.domain.SurfingWeatherModel;
import com.wheaterapp.domain.WeatherSpecification;
import com.wheaterapp.infrastructure.dto.CityWeatherDayDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ConditionWeatherTest {

    @Mock
    WeatherSpecification weatherSpecificationMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void shouldReturnTheBestWeatherByRangePoint_whenWeatherIsInRange() {
        // given

        List<CityWeatherDto> cityWeatherParams = new ArrayList<>();

        List<CityWeatherDayDto> cityWeatherList = new ArrayList<>();
        cityWeatherList.add(new CityWeatherDayDto("Tarnów",10,10,"2023-07-04"));
        cityWeatherList.add(new CityWeatherDayDto("Tarnów",10,10,"2023-07-05"));
        cityWeatherParams.add(new CityWeatherDto("Tarnów",cityWeatherList));

        SurfingWeatherModel surfingWeatherModel = new SurfingWeatherModel();

        ConditionWeather conditionWeather = new ConditionWeather();
        when(weatherSpecificationMock.isInRange(10,10)).thenReturn(true);
        when(weatherSpecificationMock.calculateTheBestWeatherConditions(10,10)).thenReturn(10.0);

        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = new ConditionWeather("Tarnów",10,10,10);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void shouldReturnEmptyConditionWeatherObject_ParamsIsOutOnRange() {
        // given
        List<CityWeatherDto> cityWeatherParams = new ArrayList<>();
        List<CityWeatherDayDto> cityWeatherList = new ArrayList<>();
        cityWeatherList.add(new CityWeatherDayDto("Tarnów",10,10,"2023-07-04"));
        cityWeatherList.add(new CityWeatherDayDto("Tarnów",10,10,"2023-07-05"));
        cityWeatherParams.add(new CityWeatherDto("Tarnów",cityWeatherList));


        ConditionWeather conditionWeather = new ConditionWeather();
        when(weatherSpecificationMock.isInRange(10,10)).thenReturn(false);


        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = ConditionWeather.builder().build();
        Assertions.assertEquals(expected, result);
    }
}




