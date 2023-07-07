package com.wheaterapp.infrastructure.dto;

import lombok.Builder;

@Builder
public record RequestDateWeather(
        String startDay
) {
}
