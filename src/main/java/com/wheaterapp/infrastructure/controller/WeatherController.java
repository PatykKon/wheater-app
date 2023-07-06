package com.wheaterapp.infrastructure.controller;

import com.wheaterapp.infrastructure.dto.CityResponse;
import com.wheaterapp.infrastructure.dto.RequestDateWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private final ConditionWeatherService weatherService;

    @GetMapping("/getcity")
    public CityResponse getCity(@RequestBody RequestDateWeather requestDateWeather){
        return weatherService.getCityToSurfing(requestDateWeather);
    }
}
