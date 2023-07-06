package com.wheaterapp.infrastructure.clientapi.dto;

public record WeatherDto(
        String city,
        double temp ,
        double wind_spd,
        String datetime) {
}
