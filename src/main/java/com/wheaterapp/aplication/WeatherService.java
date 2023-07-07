package com.wheaterapp.aplication;

import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.domain.SurfingWeatherModel;
import com.wheaterapp.infrastructure.clientapi.WeatherClient;
import com.wheaterapp.infrastructure.clientapi.dto.WeatherDataDto;
import com.wheaterapp.infrastructure.dto.CityResponse;
import com.wheaterapp.infrastructure.dto.CityWeatherDayDto;
import com.wheaterapp.infrastructure.dto.CityWeatherDto;
import com.wheaterapp.infrastructure.dto.RequestDateWeather;
import com.wheaterapp.infrastructure.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherMapper weatherMapper;
    private final ConditionWeather conditionWeather;
    private final WeatherClient weatherClient;
    private final DateFilter dateFilter;



    public CityResponse getCityToSurfing(RequestDateWeather requestDateWeather) {

        SurfingWeatherModel surfingWeatherModel = new SurfingWeatherModel();

        List<CityWeatherDto> weatherCityWeatherParams = getCityWeatherListInRequestDataRange(requestDateWeather);
        ConditionWeather theBestWeather = conditionWeather.getTheBestWeatherByRangePoint(weatherCityWeatherParams,surfingWeatherModel);
        CityResponse cityResponse = weatherMapper.mapToCityResponse(theBestWeather);
        return cityResponse;
    }
    public List<CityWeatherDto> getCityWeatherListInRequestDataRange(RequestDateWeather requestDate) {

        List<CityWeatherDto> cityWeatherForAllDay = new ArrayList<>();

        List<WeatherDataDto> weatherData = weatherClient.getWeatherForCity();

        for (WeatherDataDto weatherDataDto : weatherData) {
            CityWeatherDto cityWeather = dateFilter.filteringWeatherByRequestDate(weatherDataDto, requestDate);
            cityWeatherForAllDay.add(cityWeather);
        }
        return cityWeatherForAllDay;
    }
}
