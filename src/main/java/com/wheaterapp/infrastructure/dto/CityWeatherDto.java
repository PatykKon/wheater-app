package com.wheaterapp.infrastructure.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CityWeatherDto(
        String city,
        List<CityWeatherDayDto> cityWeatherDay


) {

}
