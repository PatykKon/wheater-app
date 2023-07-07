package com.wheaterapp.configuration;

import com.wheaterapp.domain.ConditionWeather;
import com.wheaterapp.domain.SurfingWeatherModel;
import com.wheaterapp.infrastructure.mapper.WeatherMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public SurfingWeatherModel surfingWeatherModel() {
        return new SurfingWeatherModel();
    }

    @Bean
    public ConditionWeather conditionWeather() {
        return new ConditionWeather();
    }
    @Bean
    public WeatherMapper weatherMapper(){
        return new WeatherMapper();
    }
}
