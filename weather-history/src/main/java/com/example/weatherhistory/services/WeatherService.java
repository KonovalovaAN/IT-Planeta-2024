package com.example.weatherhistory.services;

import com.example.weatherhistory.entities.Weather;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherService {
    Weather getWeatherByRegionId(Long regionId);
    List<Weather> searchWeather(LocalDateTime startDateTime, LocalDateTime endDateTime, Long regionId, String weatherCondition, int from, int size);
    Weather addWeather(Weather weather);
    Weather updateWeather(Long regionId, Weather weather);
    void deleteWeather(Long regionId);
    Weather addWeatherToRegion(Long regionId, Long weatherId);
    void removeWeatherFromRegion(Long regionId, Long weatherId);
}
