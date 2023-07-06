package com.wheaterapp.infrastructure.clientapi.dto;

import java.util.List;

public record WeatherDataDto(
        List<WeatherDto> data,
        String city_name
) {
}
