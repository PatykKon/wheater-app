package com.wheaterapp.infrastructure.clientapi.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record WeatherDto(
        String city,
        Double temp ,
        Double wind_spd,
        String datetime) {
}
