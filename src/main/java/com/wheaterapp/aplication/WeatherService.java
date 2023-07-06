package com.wheaterapp.aplication;

import com.wheaterapp.domain.CityWeather;
import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.domain.SurfingWeatherModel;
import com.wheaterapp.infrastructure.clientapi.WeatherClient;
import com.wheaterapp.infrastructure.clientapi.dto.WeatherDataDto;
import com.wheaterapp.infrastructure.dto.CityResponse;
import com.wheaterapp.infrastructure.dto.RequestDateWeather;
import com.wheaterapp.infrastructure.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherMapper weatherMapper;
    private final ConditionWeather conditionWeather;
    private final CityWeather cityWeather;
    private final WeatherClient weatherClient;
    private final DateFilter dateFilter;



    public CityResponse getCityToSurfing(RequestDateWeather requestDateWeather){

        SurfingWeatherModel surfingWeatherModel = new SurfingWeatherModel();

        List<CityWeather> weatherCityWeatherParams = getCityListWithWeatherParams(requestDateWeather);
        ConditionWeather theBestWeather = conditionWeather.getTheBestWeatherByRangePoint(weatherCityWeatherParams,surfingWeatherModel);
        CityResponse cityResponse = weatherMapper.mapToCityResponse(theBestWeather);
        return cityResponse;
    }
    public List<CityWeather> getCityListWithWeatherParams(RequestDateWeather requestDate) {

        List<CityWeather> cityWithWeatherParams = new ArrayList<>();

        List<WeatherDataDto> weatherData = weatherClient.getWeatherForCity();

        for (WeatherDataDto weatherDataDto : weatherData) {
            String city = weatherDataDto.city_name();
            List<CityWeather> weatherInRequestDate = dateFilter.filteringWeatherByRequestDate(weatherDataDto, requestDate);
            CityWeather cityWithWeatherParam = cityWeather.addWeatherParamsToCity(city,weatherInRequestDate);
            cityWithWeatherParams.add(cityWithWeatherParam);

        }
        return cityWithWeatherParams;
    }
}
