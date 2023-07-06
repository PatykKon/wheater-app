package com.wheaterapp.infrastructure.dto;

public record CityResponse(
        String city,
        double temp,
        double windSpeed
) {
}
