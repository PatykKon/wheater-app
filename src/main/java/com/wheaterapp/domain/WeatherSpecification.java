package com.wheaterapp.domain;

public interface WeatherSpecification {
    boolean isTemperatureInRange(double temperature);

    boolean isWindSpeedInRange(double windSpeed);

    boolean isInRange(double temp, double windSpeed);

    double calculateTheBestWeatherConditions(double windSpeed,double temperature);
}
