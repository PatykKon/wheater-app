package com.wheaterapp.infrastructure.DTO;

import java.util.List;

public record WeatherDataDto(
        List<WeatherDto> data,
        String city_name
) {
}
