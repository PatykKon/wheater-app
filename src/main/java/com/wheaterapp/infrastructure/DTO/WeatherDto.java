package com.wheaterapp.infrastructure.DTO;

public record WeatherDto(
        String city,
        double temp ,
        double wind_spd,
        String datetime) {
}
