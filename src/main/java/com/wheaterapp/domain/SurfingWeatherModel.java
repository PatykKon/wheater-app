package com.wheaterapp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PACKAGE)
public class SurfingWeatherModel implements WeatherSpecification{

    private final static double TEMP_RANGE_MIN = 5;
    private final static double TEMP_RANGE_MAX = 35;
    private final static double WIND_RANGE_MIN = 5;
    private final static double WIND_RANGE_MAX = 35;

    private final static int COMPUTATIONAL_CONSTANT = 3;


    @Override
    public boolean isTemperatureInRange(double temperature) {
        return (temperature >= TEMP_RANGE_MIN && temperature <= TEMP_RANGE_MAX);
    }
    @Override
    public boolean isWindSpeedInRange(double windSpeed){
        return (windSpeed>=WIND_RANGE_MIN && windSpeed <=WIND_RANGE_MAX);
    }
    @Override
    public boolean isInRange(double temp, double windSpeed){
        return (isTemperatureInRange(temp)) && (isWindSpeedInRange(windSpeed));

    }
    @Override
    public double calculateTheBestWeatherConditions(double windSpeed,double temperature){
        return windSpeed*temperature+COMPUTATIONAL_CONSTANT;
    }
}
