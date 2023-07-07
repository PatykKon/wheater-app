package com.wheaterapp.infrastructure.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record CityWeatherDayDto(
        String datetime,
        double temp,
        double wind_spd,
        String city) {

}
