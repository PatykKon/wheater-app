package com.wheaterapp;


import com.wheaterapp.domain.CityWeather;
import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.domain.WeatherSpecification;
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
    void shouldReturnTheBestWeatherByRangePoint_whenOneOrMoreWeatherIsInRange() {
        // given

        List<CityWeather> cityWeatherParams = new ArrayList<>();
        cityWeatherParams.add(new CityWeather("Warszawa",5, 5,"2023-07-04"));
        cityWeatherParams.add(new CityWeather("Kraków", 10,10,"2023-07-04"));
        cityWeatherParams.add(new CityWeather("Tarnów", 11, 11,"2023-07-04"));

       when(weatherSpecificationMock.isInRange(5,5)).thenReturn(true);
       when(weatherSpecificationMock.isInRange(10,10)).thenReturn(true);
       when(weatherSpecificationMock.isInRange(11,11)).thenReturn(true);

        when(weatherSpecificationMock.calculateTheBestWeatherConditions(11,11)).thenReturn(39.0);
        when(weatherSpecificationMock.calculateTheBestWeatherConditions(10,10)).thenReturn(20.0);
        when(weatherSpecificationMock.calculateTheBestWeatherConditions(5,5)).thenReturn(10.0);

        ConditionWeather conditionWeather = new ConditionWeather();

        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = new ConditionWeather("Tarnów", 11, 11,39.0);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldReturnConditionWeather_WhenOnlyOneWeatherIsInRange() {
        // given
        List<CityWeather> cityWeatherParams = new ArrayList<>();

        cityWeatherParams.add(new CityWeather("Tarnów", 11,11,"2023-07-04"));
        cityWeatherParams.add(new CityWeather("Żelazowka", 10,10,"2023-07-04"));


        ConditionWeather conditionWeather = new ConditionWeather();
        when(weatherSpecificationMock.isInRange(11,11)).thenReturn(true);
        when(weatherSpecificationMock.isInRange(10,10)).thenReturn(false);
        when(weatherSpecificationMock.calculateTheBestWeatherConditions(11,11)).thenReturn(39.0);

        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = new ConditionWeather("Tarnów", 11, 11,39);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void shouldReturnEmptyConditionWeatherObject_WhenAllWeathersIsOutOnRange() {
        // given
        List<CityWeather> cityWeatherParams = new ArrayList<>();


        cityWeatherParams.add(new CityWeather("Tarnów", 11,11,"2023-07-04"));
        cityWeatherParams.add(new CityWeather("Żelazowka", 10,10,"2023-07-04"));

        ConditionWeather conditionWeather = new ConditionWeather();
        when(weatherSpecificationMock.isInRange(11,11)).thenReturn(false);
        when(weatherSpecificationMock.isInRange(10,10)).thenReturn(false);

        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = ConditionWeather.builder().build();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void returnEmptyObjectConditionWeather_whenOneOfParamsIsOutOfRange() {
        // given
        List<CityWeather> cityWeatherParams = new ArrayList<>();



        cityWeatherParams.add(new CityWeather("Tarnów", 10, 6, "2023-07-04"));

        when(weatherSpecificationMock.isTemperatureInRange(6)).thenReturn(false);
        when(weatherSpecificationMock.isInRange(6,10)).thenReturn(false);

        ConditionWeather conditionWeather = new ConditionWeather();

        // when
        ConditionWeather result = conditionWeather.getTheBestWeatherByRangePoint(cityWeatherParams, weatherSpecificationMock);

        // then
        ConditionWeather expected = ConditionWeather.builder().build();
        Assertions.assertEquals(expected, result);;
    }
}



